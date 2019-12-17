import ego.EgoBaseVisitor
import ego.EgoParser
import org.antlr.v4.runtime.tree.TerminalNode
import java.lang.StringBuilder

class EgoVisitor : EgoBaseVisitor<String>() {
    private val terms = mutableMapOf(
        Pair("nextInt", "int"), Pair("nextChar", "char"), Pair("nextLong", "long"),
        Pair("nextDouble", "double"), Pair("nextLine", "char*"),
        Pair("printInt", "void"), Pair("printChar", "void"), Pair("printLong", "void"),
        Pair("printDouble", "void"), Pair("printString", "void")
    )

    private val formats = mapOf(Pair("int", "%d"), Pair("long long", "%lld"), Pair("char", "%c"), Pair("char*", "%s"), Pair("double", "%lf"))

    private var nesting = 0

    private fun defaultPrintImplementation(name: String): String {
        val funcType = terms[name]
        val varType = argTypes[name]
        return """
            $funcType $name($varType arg) {
                printf("${formats[varType]}", arg);
            }
        """.trimIndent()
    }

    private fun defaultNextLineImplementation(): String {
        return """
            char* nextLine() {
                char* result;
                size_t len = 0;
                getline(&result, &len, stdin);
                return result;
            }
        """.trimIndent()
    }

    private fun defaultNextImplementation(name: String): String {
        if (name.endsWith("Line")) return defaultNextLineImplementation()
        val funcType = terms[name]
        val varType = argTypes[name]
        return """
            $funcType $name() {
                $varType result;
                scanf("${formats[varType]}", &result);
                return result;
            }
        """.trimIndent()
    }

    private fun defaultImplementation(name: String) = when {
        name.startsWith("next") -> defaultNextImplementation(name)
        name.startsWith("print") -> defaultPrintImplementation(name)
        else -> ""
    }

    private val types = mapOf(
        Pair("Int", "int"), Pair("Char", "char"), Pair("String", "char*"),
        Pair("Double", "double"), Pair("Long", "long long"), Pair("Nothing", "void")
    )

    private val argTypes = mapOf(
        Pair("nextInt", "int"), Pair("nextChar", "char"), Pair("nextLong", "long"),
        Pair("nextDouble", "double"), Pair("nextLine", "char*"),
        Pair("printInt", "int"), Pair("printLong", "long long"), Pair("printDouble", "double"),
        Pair("printChar", "char"), Pair("printString", "char*")
    )

    private val defaultIO = argTypes.keys.toMutableSet()

    private val variables = mutableSetOf<String>()
    private val functions = mutableSetOf<String>()

    override fun visitProgram(ctx: EgoParser.ProgramContext?): String {
        return visit(ctx?.blocks())
    }

    override fun visitBlocks(ctx: EgoParser.BlocksContext?): String {
        val functions = mutableListOf<String>()
        val lines = mutableListOf<String>()
        for (block in ctx!!.children) {
            val result = visit(block)
            if (block.getChild(0) is EgoParser.FunctionContext) {
                functions.add(result)
            } else {
                if (block.text != "\n") lines.add(result)
            }
        }
        return """
#include "stdio.h"
${defaultIO.joinToString("\n") { name -> defaultImplementation(name) }}
${functions.joinToString("\n")}
int main() {
    ${lines.joinToString("\t")}
    return 0;
}
        """.trimIndent()
    }

    override fun visitBlock(ctx: EgoParser.BlockContext?): String {
        return visit(ctx?.getChild(0))
    }

    override fun visitLine(ctx: EgoParser.LineContext?): String {
        return "${visit(ctx?.getChild(0))};\n"
    }

    override fun visitAssignment(ctx: EgoParser.AssignmentContext?): String {
        val children = ctx!!.children
        val name = visit(children[0])
        val expression = children[2]
        val rvalue = expression.getChild(0)
        var rightHandSide = visit(expression)
        val term = if (rvalue is EgoParser.CallContext) visit(rvalue.functionName()) else rightHandSide
        val casts = children.withIndex().filter { v -> v.index > 2 && v.index % 2 == 0 }.map { v -> visit(v.value) }
        var type: String? = ""
        if (name !in variables) {
            variables.add(name)
            type = terms[term]
            if (type != null) {
                terms[name] = type
                type = "$type "
            } else if (expression.getChild(0) is EgoParser.LiteralContext) {
                val literal = expression.getChild(0) as EgoParser.LiteralContext
                val inferredType = when {
                    literal.charLiteral() != null -> "char"
                    literal.stringLiteral() != null -> "char*"
                    literal.numberLiteral() != null -> "int"
                    literal.doubleLiteral() != null -> "double"
                    else -> ""
                }
                terms[name] = inferredType
                type = "$inferredType "
            }
        }
        for (cast in casts) {
            rightHandSide = "($cast)($rightHandSide)"
            type = "$cast "
        }
        return "$type$name = $rightHandSide"
    }

    override fun visitLiteral(ctx: EgoParser.LiteralContext?): String {
        return ctx!!.text
    }

    override fun visitName(ctx: EgoParser.NameContext?): String {
        return ctx!!.text
    }

    override fun visitFunction(ctx: EgoParser.FunctionContext?): String {
        val children = ctx!!.children
        val name = visit(children[1])
        val type = visit(children[6])
        val args = visit(children[3])
        terms[name] = type
        functions.add(name)
        if (name in defaultIO) defaultIO.remove(name)
        val currentVariables = variables.toMutableSet()
        val arguments = args.split(",")
        for (arg in arguments) {
            if (arg == "") continue
            val s = arg.trim().split(" ")
            val t = s[0]
            val n = s[1]
            variables.add(n)
            terms[n] = t
        }
        val scope = visit(children[7])
        val newcomers = variables.minus(currentVariables)
        for (n in newcomers) {
            terms.remove(n)
            variables.remove(n)
        }
        return "$type $name($args) $scope"
    }

    override fun visitCall(ctx: EgoParser.CallContext?): String {
        return ctx!!.text
    }

    override fun visitFargs(ctx: EgoParser.FargsContext?): String {
        return ctx!!.text
    }

    override fun visitArgs(ctx: EgoParser.ArgsContext?): String {
        val children = ctx!!.children
        return if (children != null) visit(children[0]) else ""
    }

    override fun visitArg(ctx: EgoParser.ArgContext?): String {
        val children = ctx!!.children
        return "${visit(children[2])} ${visit(children[0])}"
    }

    override fun visitMultiargs(ctx: EgoParser.MultiargsContext?): String {
        return ctx!!.children.filter { t -> t !is TerminalNode }.joinToString(", ") { arg -> visit(arg) }
    }

    override fun visitMultiarg(ctx: EgoParser.MultiargContext?): String {
        val children = ctx!!.children
        return if (children.size == 1) visit(children[0]) else children.joinToString(", ") { arg -> visit(arg) }
    }

    override fun visitFunctionName(ctx: EgoParser.FunctionNameContext?): String {
        return ctx!!.text
    }

    override fun visitType(ctx: EgoParser.TypeContext?): String {
        return types[ctx!!.text] ?: error("Unreachable")
    }

    override fun visitFlow(ctx: EgoParser.FlowContext?): String {
        return visit(ctx?.getChild(0))
    }

    override fun visitIfStatement(ctx: EgoParser.IfStatementContext?): String {
        val children = ctx!!.children
        return "if (${visit(children[1])}) ${visit(children[2])}"
    }

    override fun visitWhileLoop(ctx: EgoParser.WhileLoopContext?): String {
        val children = ctx!!.children
        return "while (${visit(children[1])}) ${visit(children[2])}"
    }

    override fun visitForLoop(ctx: EgoParser.ForLoopContext?): String {
        val children = ctx!!.children
        val name = visit(children[1])
        val left = children[3].text
        val right = children[6].text
        return "for (int $name = $left; $name <= $right; $name++) ${visit(children[7])}"
    }

    override fun visitScope(ctx: EgoParser.ScopeContext?): String {
        val children = ctx!!.children
        nesting++
        val s = children.withIndex().filter { v -> v.index > 0 && v.index < children.size - 1 }
            .joinToString("") { v -> if (v.value.text == "\n") "" else "\t".repeat(nesting) + visit(v.value) }
        nesting--
        val postfix = "\t".repeat(nesting)
        return "{\n$s$postfix}"
    }

    override fun visitStatement(ctx: EgoParser.StatementContext?): String {
        return ctx!!.text
    }

    override fun visitPredicate(ctx: EgoParser.PredicateContext?): String {
        return ctx!!.text
    }

    override fun visitReturnStatement(ctx: EgoParser.ReturnStatementContext?): String {
        val children = ctx!!.children
        var rightHandSide = visit(children[1])
        val casts = children.withIndex().filter { v -> v.index > 2 && v.index % 2 == 1 }.map { v -> visit(v.value) }
        for (cast in casts) {
            rightHandSide = "($cast)($rightHandSide)"
        }
        return "return $rightHandSide"
    }

    override fun visitRvalue(ctx: EgoParser.RvalueContext?): String {
        val children = ctx!!.children
        val result = StringBuilder()
        for (v in children.withIndex()) {
            if (v.index % 2 == 1) {
                result.append(" ${v.value.text} ")
            } else result.append(visit(v.value))
        }
        return result.toString()
    }

    override fun visitNumberLiteral(ctx: EgoParser.NumberLiteralContext?): String {
        return ctx!!.text
    }

    override fun visitDoubleLiteral(ctx: EgoParser.DoubleLiteralContext?): String {
        return ctx!!.text
    }

    override fun visitCharLiteral(ctx: EgoParser.CharLiteralContext?): String {
        return ctx!!.text
    }

    override fun visitStringLiteral(ctx: EgoParser.StringLiteralContext?): String {
        return ctx!!.text
    }
}