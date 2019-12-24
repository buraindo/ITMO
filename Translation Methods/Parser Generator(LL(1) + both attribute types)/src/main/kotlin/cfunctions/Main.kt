package cfunctions

import guru.nidi.graphviz.attribute.Color
import guru.nidi.graphviz.engine.Format
import guru.nidi.graphviz.engine.Graphviz
import guru.nidi.graphviz.model.Factory.mutGraph
import guru.nidi.graphviz.model.Factory.mutNode
import guru.nidi.graphviz.model.MutableGraph
import guru.nidi.graphviz.model.MutableNode
import java.io.File

class Visualizer(private val name: String = "G") {
    private var g: MutableGraph = mutGraph(this.name)

    init {
        g.isDirected = true
    }

    private fun getNodeId(node: CFunctionsParser.Node): String = System.identityHashCode(node).toString()

    private fun buildGraph(nodeTree: CFunctionsParser.Node, node: MutableNode) {

        if (nodeTree.children.isEmpty())
            node.add(Color.GREEN1)

        nodeTree.children.forEach {
            val curNode = mutNode(getNodeId(it), true).add("label", it.text)
            g.add(curNode)
            node.addLink(curNode)
            buildGraph(it, curNode)
        }
    }

    fun visualize(root: CFunctionsParser.Node) {
        val rootLocal = mutNode(getNodeId(root), true).add("label", root.text)
        g.add(rootLocal)
        buildGraph(root, rootLocal)
        Graphviz.fromGraph(g).render(Format.PNG).toFile(File("src/main/resources/img/CFunctions.png"))
    }
}

fun main() {
    val input = File("src/main/resources/tests/whitespaces1.txt").readText()
    val lexer = CFunctionsLexer(input)
    val parser = CFunctionsParser(lexer)
    val function = parser.function()
    println(function.`val`)
    Visualizer().visualize(function)
}