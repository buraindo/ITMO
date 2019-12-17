package inference

import parser.CLOSE
import parser.OPEN
import parser.Variable
import parser.WHITESPACE

sealed class Tau

data class Type(val v: Variable) : Tau() {
    override fun toString() = v.toString()
}

data class Impl(var l: Tau, var r: Tau) : Tau() {
    override fun toString() = OPEN + l.toString() + WHITESPACE + IMPLIES + WHITESPACE + r.toString() + CLOSE
}

const val IMPLIES = "->"