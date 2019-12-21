package calculator

fun main() {
    val lexer = CalculatorLexer("2 + 3 - (6/2) * (4*45)")
    val parser = CalculatorParser(lexer)
    val n = parser.e()
    println(n.`val`)
}