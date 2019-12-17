import inference.Inference
import parser.Parser

fun main() {
    val parser = Parser(readLine()!!)
    val expression = parser.parse()
    Inference(parser.variables()).inference(expression)
}