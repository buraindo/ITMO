package parser

sealed class Term {
    abstract fun substitute(x: Variable, e: Term): Term
    abstract fun rename(m: HashMap<String, String>)
    abstract fun copy(): Term
    abstract fun reduce(expected: Application, e: Term): Term
}

data class Variable(var name: String) : Term() {
    override fun toString() = name
    override fun substitute(x: Variable, e: Term) = if (this == x) e else this
    override fun rename(m: HashMap<String, String>) {
        name = m[name] ?: name
    }

    override fun copy(): Term {
        return Variable(this.name)
    }

    override fun reduce(expected: Application, e: Term) = this
}

data class Abstraction(var x: Variable, var e: Term) : Term() {
    override fun toString() = OPEN + (LAMBDA + x.toString()) + DOT + e.toString() + CLOSE
    override fun substitute(x: Variable, e: Term): Term {
        this.e = this.e.substitute(x, e)
        return this
    }

    override fun rename(m: HashMap<String, String>) {
        val name = freeVariable()
        if (m.containsKey(x.name)) {
            val old = m[x.name]!!
            m[x.name] = name
            e.rename(m)
            m[x.name] = old
        } else {
            m[x.name] = name
            e.rename(m)
            m.remove(x.name)
        }
        x.name = name
    }

    override fun copy(): Term {
        return Abstraction(this.x.copy() as Variable, this.e.copy())
    }

    override fun reduce(expected: Application, e: Term): Term {
        this.e = this.e.reduce(expected, e)
        return this
    }
}

data class Application(var l: Term, var r: Term) : Term() {
    override fun toString() = OPEN + l.toString() + WHITESPACE + r.toString() + CLOSE
    override fun substitute(x: Variable, e: Term): Term {
        this.l = this.l.substitute(x, e)
        this.r = this.r.substitute(x, e)
        return this
    }

    override fun rename(m: HashMap<String, String>) {
        l.rename(m)
        r.rename(m)
    }

    override fun copy(): Term {
        return Application(this.l.copy(), this.r.copy())
    }

    override fun reduce(expected: Application, e: Term): Term {
        return if (expected == this) e else {
            l = l.reduce(expected, e)
            r = r.reduce(expected, e)
            this
        }
    }
}

const val LAMBDA = '\\'
const val DOT = '.'
const val OPEN = '('
const val CLOSE = ')'
const val WHITESPACE = ' '
const val EOF = '\n'
var counter = 0

fun freeVariable(): String {
    return "t${counter++}"
}