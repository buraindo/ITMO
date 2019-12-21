package generator

fun main(args: Array<String>) {
    val grammar = makeGrammar(args[0])
    LexerGenerator(grammar).generate()
    ParserGenerator(grammar).generate()
}