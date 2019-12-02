package lexer

type TokenType int
type Token struct {
	Type TokenType
	Text string
}

var TypeToName = map[int]string{
	int(Identifier): "Identifier",
	int(Opening):    "Opening",
	int(Closing):    "Closing",
	int(Comma):      "Comma",
	int(Semicolon):  "Semicolon",
	int(Asterisk):   "Asterisk",
	int(Whitespace): "Whitespace",
	int(End):        "End",
}

const (
	Identifier TokenType = iota
	Opening
	Closing
	Comma
	Semicolon
	Asterisk
	Whitespace
	End
)
