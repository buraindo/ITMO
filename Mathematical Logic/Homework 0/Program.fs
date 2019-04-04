module Program

open Parser
open Lexer
open Microsoft.FSharp.Text

let expression = "P1’->!QQ->!R10&S|!T&U&V"

let ParseSql expression = 
    let lexbuf = Lexing.LexBuffer<_>.FromString expression
    let result = start tokenize lexbuf   
    result

let parsed = (ParseSql expression)
let mutable strRepresent = sprintf "%A" parsed

for symbol in symbols do
    strRepresent <- strRepresent.Replace(symbol.Key, symbol.Value)

printfn "%s" strRepresent