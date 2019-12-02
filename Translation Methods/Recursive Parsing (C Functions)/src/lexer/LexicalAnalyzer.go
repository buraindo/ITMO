package lexer

import (
	"bytes"
	"fmt"
	"unicode"
)

const (
	eof        = 0
	comma      = ','
	opening    = '('
	closing    = ')'
	asterisk   = '*'
	semicolon  = ';'
	whitespace = ' '
)

var (
	current    Token
	expression string
	char       rune
	index      int
)

func Init(e string) {
	current = Token{-1, ""}
	index = 0
	expression = e
	char = rune(expression[0])
}

func Next(skip bool) Token {
	if skip {
		for index < len(expression) && unicode.IsSpace(rune(expression[index])) {
			move()
		}
	}
	switch char {
	case eof:
		current = Token{End, string(eof)}
		return current
	case comma:
		current = Token{Comma, string(comma)}
	case opening:
		current = Token{Opening, string(opening)}
	case closing:
		current = Token{Closing, string(closing)}
	case asterisk:
		current = Token{Asterisk, string(asterisk)}
	case semicolon:
		current = Token{Semicolon, string(semicolon)}
	case whitespace:
		current = Token{Whitespace, string(whitespace)}
	default:
		current = Token{Identifier, identifier()}
	}
	move()
	return current
}

func Position() int {
	return index
}

func Back() {
	index--
	char = rune(expression[index])
}

func move() {
	index++
	if index == len(expression) {
		char = eof
	} else {
		char = rune(expression[index])
	}
}

func identifier() string {
	result := bytes.Buffer{}
	if !(unicode.IsLetter(char) || char == '_') {
		panic(lexicalError{char, index})
	}
	for isValid(char) {
		result.WriteRune(char)
		move()
	}
	Back()
	return result.String()
}

func isValid(c rune) bool {
	return unicode.IsDigit(c) || unicode.IsLetter(c) || c == '_'
}

type lexicalError struct {
	char     rune
	position int
}

func (e lexicalError) Error() string {
	return fmt.Sprintf("Unexpected symbol '%c' at %d", e.char, e.position)
}
