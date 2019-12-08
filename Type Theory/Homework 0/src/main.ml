open Expression;;
open Buffer;;
open Printf;;

let (>>) x f = f x;;

let string_of_expression expression = 
  let buf = create 1000 in
  let rec s_t t = match t with
    | Variable v -> add_string buf v
    | Abstraction (x, e) -> add_string buf "(\\"; add_string buf x; add_char buf '.'; s_t e; add_char buf ')'
    | Application (l, r) -> add_char buf '('; s_t l; add_char buf ' '; s_t r; add_char buf ')'
  in s_t expression;
  contents buf;;

let set finished =
  finished := true;;

let read() =
  let buf = create 42 in
  let finished = ref false in
  while not !finished do
    try
      let line = read_line() in
      add_string buf line;
      add_char buf ' ';
    with
      | End_of_file -> set finished
  done;
  contents buf;;

read() >> Lexing.from_string >> Parser.main Lexer.main >> string_of_expression >> fprintf stdout "%s\n";;