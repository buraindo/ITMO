//
// Created by buraindo on 13.11.18.
//

#include <iostream>
#include "Array.h"

template<class T, class Compare>
T minimum(const Array<T>& array, Compare compare) {
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

bool less (int lhs, int rhs) {
    return lhs < rhs;
}

struct Greater {
    bool operator()(int lhs, int rhs) const {
        return rhs < lhs;
    }
};

int main() {
    Array<int> arr(3);
    Array<int> arr2(4);
    std::cout << arr.size() << std::endl;
    arr[0] = 3;
    arr[1] = 5;
    std::cout << arr[0] << " " << arr[1] << " " << arr[2] << std::endl;
    std::cout << arr2[0] << " " << arr2[1] << " " << arr2[2] << " " << arr2[3] << std::endl;
    arr2 = arr;
    std::cout << arr2[0] << " " << arr2[1] << " " << arr2[2] << std::endl;
    Array<int> arr3(arr);
    std::cout << arr3[0] << " " << arr3[1] << " " << arr3[2] << std::endl;
    int min1 = minimum(arr, less);
    std::cout << min1 << std::endl;
    int min2 = minimum(arr3, Greater());
    std::cout << min2 << std::endl;
    Array<Array<int>> nested1(5, arr);
    Array<Array<Array<int>>> nested2(2, nested1);
    flatten(nested2, std::cout);
    std::cout << std::endl;
    Array<double> doubles(10, 5.3);
    flatten(doubles, std::cout);
    return 0;
}