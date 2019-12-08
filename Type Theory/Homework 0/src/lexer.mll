{
open Parser
}

let variable = ['a' - 'z'] ['a' - 'z' '0' - '9' '\'']*
let white = ['\t' ' ']+

rule main = parse
        | variable as v { VAR(v) }
        | "\\"          { LAMBDA }
        | "."           { DOT }
        | "("           { OPEN }
        | ")"           { CLOSE }
        | white         { main lexbuf }
        | eof           { EOF }  