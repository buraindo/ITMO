SRC_DIR = src
OUT_DIR = out
SOURCES = $(shell find ${SRC_DIR} -type f -name "*.kt" -not -path "*/test/*")
PACK_OUT_FILE = hw1.zip

.PHONY: pack

all: compile

compile: ${SOURCES}
	kotlinc -cp ${SRC_DIR} -d ${OUT_DIR} $^

run:
	kotlin -cp .:${OUT_DIR} MainKt

clean:
	rm -rf ${OUT_DIR}

pack:
	zip ${PACK_OUT_FILE} -r Makefile src