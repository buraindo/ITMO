package cfunctions

import java.io.File

fun main() {
    val input = File("src/main/resources/tests/whitespaces1.txt").readText()
    val lexer = CFunctionsLexer(input)
    val parser = CFunctionsParser(lexer)
    println(parser.function().`val`)
}