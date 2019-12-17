package parser

sealed class Term
data class Variable(val name: String, var alpha: Int, var free: Boolean) : Term() {
    override fun toString() = name
}

data class Abstraction(var x: Variable, var e: Term) : Term() {
    override fun toString() = OPEN + (LAMBDA + x.toString()) + DOT + e.toString() + CLOSE
}

data class Application(var l: Term, var r: Term) : Term() {
    override fun toString() = OPEN + l.toString() + WHITESPACE + r.toString() + CLOSE
}

const val LAMBDA = '\\'
const val DOT = '.'
const val OPEN = '('
const val CLOSE = ')'
const val WHITESPACE = ' '
const val EOF = '\n'
