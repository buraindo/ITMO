import ego.EgoLexer
import ego.EgoParser
import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream
import java.io.File

fun main() {
    val input = File("src/main/resources/input.txt").readText()
    val lexer = EgoLexer(CharStreams.fromString(input))
    val parser = EgoParser(CommonTokenStream(lexer))
    val program = parser.program()
    val visitor = EgoVisitor()
    File("src/main/resources/output.txt").writeText(visitor.visit(program))
}