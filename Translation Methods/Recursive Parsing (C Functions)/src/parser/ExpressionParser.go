package parser

import (
	"bytes"
	"fmt"
	"lexer"
)

func Init(expression string) {
	lexer.Init(expression)
}

func Parse() (n *Node, e error) {
	defer func() {
		if r := recover(); r != nil {
			e = r.(error)
		}
	}()
	return function(), e
}

func function() *Node {
	result := &Node{}
	result.children = append(result.children, typeIdentifier())
	result.children = append(result.children, name())
	result.children = append(result.children, opening())
	result.children = append(result.children, args())
	result.children = append(result.children, closing())
	result.children = append(result.children, semicolon())
	result.children = append(result.children, end())
	return result
}

func typeIdentifier() *Node {
	return token(Type, lexer.Identifier)
}

func name() *Node {
	return token(Name, lexer.Identifier)
}

func variable(first int) *Node {
	next := lexer.Next(first == 0)
	result := &Node{rule: Variable}
	result.children = append(result.children, &Node{rule: typeToRule[next.Type], text: next.Text})
	if next.Type == lexer.Asterisk {
		result.children = append(result.children, variable(first+1))
	} else if next.Type != lexer.Identifier {
		panic(parseError{next.Type, []lexer.TokenType{lexer.Asterisk, lexer.Identifier}, lexer.Position()})
	}
	return result
}

func opening() *Node {
	return token(Opening, lexer.Opening)
}

func closing() *Node {
	return token(Closing, lexer.Closing)
}

func semicolon() *Node {
	return token(Semicolon, lexer.Semicolon)
}

func end() *Node {
	return token(End, lexer.End)
}

func token(r Rule, t lexer.TokenType) *Node {
	next := lexer.Next(true)
	result := &Node{rule: r, text: next.Text}
	if next.Type != t {
		panic(parseError{next.Type, []lexer.TokenType{t}, lexer.Position()})
	}
	return result
}

func args() *Node {
	result := &Node{rule: Args}
	arg := arg()
	next := lexer.Next(true)
	if next.Type == lexer.Closing {
		result.children = append(result.children, arg)
		lexer.Back()
	} else if next.Type == lexer.Comma {
		multiargs := &Node{rule: Multiargs, children: []*Node{arg, {rule: Comma, text: next.Text}}}
		result.children = append(result.children, multiargs)
		multiargs.children = append(multiargs.children, multiArg())
	} else {
		panic(parseError{next.Type, []lexer.TokenType{lexer.Comma, lexer.Closing}, lexer.Position()})
	}
	return result
}

func arg() *Node {
	next := lexer.Next(true)
	result := &Node{rule: Arg}
	if next.Type == lexer.Identifier {
		result.children = append(result.children, &Node{rule: Type, text: next.Text})
		result.children = append(result.children, variable(0))
	} else if next.Type == lexer.Closing {
		lexer.Back()
	} else {
		panic(parseError{next.Type, []lexer.TokenType{lexer.Identifier, lexer.Closing}, lexer.Position()})
	}
	return result
}

func multiArg() *Node {
	result := &Node{rule: Multiarg}
	arg := &Node{rule: Arg, children: []*Node{typeIdentifier(), variable(0)}}
	result.children = append(result.children, arg)
	next := lexer.Next(true)
	switch next.Type {
	case lexer.Closing:
		lexer.Back()
	case lexer.Comma:
		result.children = append(result.children, &Node{rule: Comma, text: next.Text}, multiArg())
	default:
		panic(parseError{next.Type, []lexer.TokenType{lexer.Comma, lexer.Closing}, lexer.Position()})
	}
	return result
}

type parseError struct {
	actual   lexer.TokenType
	expected []lexer.TokenType
	position int
}

func (e parseError) Error() string {
	return fmt.Sprintf("Unexpected token %s at %d, expected %s", lexer.TypeToName[int(e.actual)], e.position, or(e.expected))
}

func or(expected []lexer.TokenType) string {
	result := bytes.Buffer{}
	for i, v := range expected {
		if i == len(expected)-1 {
			break
		}
		result.WriteString(lexer.TypeToName[int(v)] + " or ")
	}
	result.WriteString(lexer.TypeToName[int(expected[len(expected)-1])])
	return result.String()
}
