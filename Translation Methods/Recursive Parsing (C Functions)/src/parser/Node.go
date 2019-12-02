package parser

import (
	"bytes"
	"lexer"
)

type Rule int

const (
	Type Rule = iota
	Name
	Opening
	Args
	Closing
	Semicolon
	Arg
	Variable
	Multiargs
	Multiarg
	Comma
	Asterisk
	End
	whitespace string = " "
)

var typeToRule = map[lexer.TokenType]Rule{
	lexer.Identifier: Name,
	lexer.Asterisk:   Asterisk,
	lexer.Closing:    Closing,
	lexer.Comma:      Comma,
}

type Node struct {
	rule     Rule
	text     string
	children []*Node
}

func (node *Node) String() string {
	result := bytes.Buffer{}
	if node.rule != End {
		result.WriteString(node.text)
	}
	for _, t := range node.children {
		result.WriteString(t.String())
	}
	if node.rule == Type || node.rule == Comma {
		result.WriteString(whitespace)
	}
	return result.String()
}
