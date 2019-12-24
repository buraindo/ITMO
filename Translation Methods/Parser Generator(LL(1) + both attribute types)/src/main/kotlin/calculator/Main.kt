package calculator

fun main() {
    val lexer = CalculatorLexer("(1 ^ ((4 - 2) & (2 - 3))) | (5 - (!5))")
    val parser = CalculatorParser(lexer)
    val n = parser.start()
    println(n.`val`)
    println(1 xor 4 - 2 and 2 - 3 or 5 - (-6))
}
