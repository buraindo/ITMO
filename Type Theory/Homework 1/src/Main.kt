import parser.*

lateinit var term: Term

fun findLeftMostRedux(t: Term): Application? {
    return when (t) {
        is Application -> if (t.l is Abstraction) t else findLeftMostRedux(t.l) ?: findLeftMostRedux(t.r)
        is Variable -> null
        is Abstraction -> findLeftMostRedux(t.e)
    }
}

var counter = 0

fun betaReduction(m: Int, k: Int) {
    println(term)
    while (counter < m) {
        val redux = findLeftMostRedux(term) ?: return
        val a = (redux.l as Abstraction)
        val l = a.e.copy()
        l.rename(hashMapOf())
        val reduced = l.substitute(a.x, redux.r)
        term = term.reduce(redux, reduced)
        counter++
        if (counter % k == 0) {
            println(term)
        }
    }
}

fun main() {
    val input = readLine()!!.split(" ").map { s -> s.toInt() }
    val m = input[0]
    val k = input[1]
    val parser = Parser(readLine()!!)
    val expression = parser.parse()
    term = expression
    betaReduction(m, k)
    if (counter % k != 0) {
        println(term)
    }
}