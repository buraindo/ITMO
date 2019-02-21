.PHONY: all build run

all: build run
build:
	dotnet build --configuration Release --verbosity m
run:
	dotnet run --no-build --configuration Release