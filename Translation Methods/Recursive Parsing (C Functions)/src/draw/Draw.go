package main

import (
	"github.com/bradleyjkemp/memviz"
	"os"
	"parser"
)

func draw(node *parser.Node) {
	file, _ := os.Create("res/gv/graph.gv")
	memviz.Map(file, &node)
}

func main() {
	expression := os.Args[1]
	parser.Init(expression)
	function, exception := parser.Parse()
	if exception == nil {
		draw(function)
		println(function.String())
	}
}
