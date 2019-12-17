package parser

class Parser(private var s: String) {
    sealed class Token {
        object Open : Token()
        object Close : Token()
        object Lambda : Token()
        object Dot : Token()
        object Eof : Token()
        data class Identifier(val name: String) : Token()
    }

    private var ch = s[0]
    private var i = 0
    private var l = s.length
    private lateinit var cur: Token

    init {
        next()
    }

    private fun next(): Token {
        while (i < l && s[i].isWhitespace()) {
            move()
        }
        cur = when (ch) {
            EOF -> Token.Eof
            LAMBDA -> Token.Lambda
            OPEN -> Token.Open
            CLOSE -> Token.Close
            DOT -> Token.Dot
            else -> identifier()
        }
        if (cur != Token.Eof) move()
        return cur
    }

    private fun identifier(): Token {
        val res = StringBuilder()
        while (ch.isValid()) {
            res.append(ch)
            move()
        }
        back()
        return Token.Identifier(res.toString())
    }

    private fun move() {
        i++
        ch = if (i == l) EOF else s[i]
    }

    private fun back() {
        i--
        ch = s[i]
    }

    private fun Char.isValid(): Boolean {
        return isLetterOrDigit() || this == '\''
    }

    private fun atom(m: MutableMap<String, Variable>) = when (cur) {
        Token.Open -> {
            next()
            expression(m).also { next() }
        }
        else -> {
            val name = (cur as Token.Identifier).name
            if (m.containsKey(name)) {
                m[name]!!
            } else {
                val result = Variable(name)
                m[name] = result
                result
            }.also { next() }
        }
    }

    private fun abstraction(m: MutableMap<String, Variable>): Term {
        val name = (next() as Token.Identifier).name
        val v = Variable(name)
        next()
        next()
        m[name] = v
        val result = Abstraction(v, expression(m))
        m.remove(name)
        return result
    }

    private fun application(current: Term, m: MutableMap<String, Variable>): Term = when (cur) {
        Token.Close, Token.Lambda, Token.Eof -> current
        else -> application(Application(current, atom(m)), m)
    }

    private fun rest(current: Term, m: MutableMap<String, Variable>) = when (cur) {
        Token.Close, Token.Eof -> current
        else -> Application(current, abstraction(m))
    }

    private fun expression(m: MutableMap<String, Variable>): Term = when (cur) {
        Token.Lambda -> abstraction(m)
        else -> rest(application(atom(m), m), m)
    }

    fun parse(): Term {
        return expression(mutableMapOf())
    }
}
