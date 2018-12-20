#include <iostream>
#include <sstream>
#include <map>
#include <vector>
#include "BadFromString.h"

std::map<std::string, std::string> typeName;

void init() {
    typeName["i"] = "integer";
    typeName["c"] = "char";
    typeName["d"] = "double";
    typeName["s"] = "short";
    typeName["l"] = "long";
    typeName["NSt7__cxx1112basic_stringIcSt11char_traitsIcESaIcEEE"] = "string";
    typeName["b"] = "bool";
}

template<class Type>
Type fromString(const std::string& input) {
    Type result;
    std::istringstream reader (input);
    reader >> std::noskipws >> result;
    bool failed = reader.fail();
    char remainder;
    reader >> std::noskipws >> remainder;
    if (failed || !reader.eof()) {
        std::string message = "Failed to read a variable of type " + typeName[typeid(Type).name()] + " for an input string '" + input + "'";
        throw BadFromString(message);
    }
    return result;
}

int main() {
    init();
    std::vector<std::string> strings = {"123", "123.2", "abc", "123123123123123123", "a", "", " ", " 1337", "125 ", "1"};
    for (const auto &input : strings) {
        try {
            auto result = fromString<int>(input);
            std::cout << result << std::endl;
        } catch (BadFromString &badFromString) {
            std::cerr << badFromString.what() << std::endl;
        }
        try {
            auto result = fromString<double>(input);
            std::cout << result << std::endl;
        } catch (BadFromString &badFromString) {
            std::cerr << badFromString.what() << std::endl;
        }
        try {
            auto result = fromString<std::string>(input);
            std::cout << result << std::endl;
        } catch (BadFromString &badFromString) {
            std::cerr << badFromString.what() << std::endl;
        }
        try {
            auto result = fromString<char>(input);
            std::cout << result << std::endl;
        } catch (BadFromString &badFromString) {
            std::cerr << badFromString.what() << std::endl;
        }
        try {
            auto result = fromString<long>(input);
            std::cout << result << std::endl;
        } catch (BadFromString &badFromString) {
            std::cerr << badFromString.what() << std::endl;
        }
        try {
            auto result = fromString<short>(input);
            std::cout << result << std::endl;
        } catch (BadFromString &badFromString) {
            std::cerr << badFromString.what() << std::endl;
        }
        try {
            auto result = fromString<bool>(input);
            std::cout << (result ? "true" : "false") << std::endl;
        } catch (BadFromString &badFromString) {
            std::cerr << badFromString.what() << std::endl;
        }
    }
    return 0;
}