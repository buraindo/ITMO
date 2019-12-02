package main

import (
	"fmt"
	"io/ioutil"
	"os"
	"parser"
	"sort"
	"strings"
)

const (
	prefix    = "test/"
	fail      = "fail"
	extension = ".txt"
)

func main() {
	dir, _ := os.Open("test")
	files, _ := dir.Readdirnames(-1)
	sort.Strings(files)
	total := len(files)
	passed := 0
	println("-----------------------------------------------------------------------")
	for _, f := range files {
		content, _ := ioutil.ReadFile(prefix + f)
		expression := string(content)
		parser.Init(expression)
		function, exception := parser.Parse()
		name := strings.TrimSuffix(f, extension)
		if strings.Contains(f, fail) {
			if exception != nil {
				println(fmt.Sprintf("Test %s successfully passed with input: '%s' and output: %s", name, content, exception.Error()))
				passed++
			} else {
				println(fmt.Sprintf("Test %s failed", name))
			}
		} else {
			if exception == nil {
				println(fmt.Sprintf("Test %s successfully passed with output: %s", name, function.String()))
				passed++
			} else {
				println(fmt.Sprintf("Test %s failed", name))
			}
		}
	}
	println("----------------------------------------------------------------------------")
	println(fmt.Sprintf("Passed %d/%d tests", passed, total))
}
