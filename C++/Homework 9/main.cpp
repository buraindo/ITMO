#include <iostream>
#include <typeinfo>
#include <tuple>
#include <stdexcept>
#include "Array.hpp"

template<class Type>
void printValues(Type value)
{
    const Type* pt = &value;
    std::cout << typeid(*pt).name() << ": " << value << '\n';
}

template<class Type, class... Args>
void printValues(Type value, Args... args)
{
    const Type* pt = &value;
    std::cout << typeid(*pt).name() << ": " << value << '\n';
    printValues(args...);
}

template<size_t F, size_t S, class... Type>
auto toPair(const std::tuple<Type...> &tuple) -> decltype(std::make_pair(std::get<F>(tuple), std::get<S>(tuple)))
{
    return std::make_pair(std::get<F>(tuple), std::get<S>(tuple));
}

int main()
{
    printValues(0, 3.5, "Hello");
    std::cout << '\n';
    Array<int> first(3);
    Array<int> second = std::move(first);
    std::cout << second.size() << '\n';
    auto t = std::make_tuple(0, 3.5, "Hello world!");
    auto p = toPair<1, 2>(t);
    std::cout << p.first << ' ' << p.second;
    return 0;
}
