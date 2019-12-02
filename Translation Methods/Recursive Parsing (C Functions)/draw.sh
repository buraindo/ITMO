export GOPATH=/home/buraindo/Documents/Programming/Go/TM2:/home/buraindo/go
go run src/draw/Draw.go "void fib(int a, string **name);"
dot -Tpng res/gv/graph.gv -o res/img/graph.png