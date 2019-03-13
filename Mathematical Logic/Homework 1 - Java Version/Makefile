MAINCLASS = Main

all: build

run:
	java -Xms256m -Xmx512m -cp src ${MAINCLASS}
	
pack:
	zip hw0.zip -r Makefile src lib

clean:
	rm -rf out
	rm -f src/parser/*.java
	rm -f src/proof/*.java
	rm -f src/*.java

build:
	javac -cp src src/Main.java
	
out:
	mkdir -p out