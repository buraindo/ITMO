//
// Created by buraindo on 16.11.18.
//
//
// Created by buraindo on 13.11.18.
//

#include <iostream>
#include "Array.hpp"

template<class T, class C>
T minimum(const Array<T>&  array, C compare) {
    sz size = array.size();
    if (size == 0) return T();
    T min = array[0];
    for (sz i = 0; i < size; i++) {
        if (compare(array[i], min)) {
            min = array[i];
        }
    }
    return min;
}

template<class T>
void flatten(const T& value, std::ostream& writer) {
    writer << value << " ";
}

template<class T>
void flatten(const Array<T>& array, std::ostream& writer) {
    for (sz i = 0; i < array.size(); i++) {
        flatten(array[i], writer);
    }
}

int main() {
    std::cout << "Accepted by a random guy when Grisha was absent" << std::endl;
    return 0;
}
