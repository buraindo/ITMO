#pragma once

#include "Array.h"
#include <algorithm>

template<class T>
Array<T>::Array(sz size, const T& value) : mSize(size), mData(new T[size]) {
    for (sz i = 0; i < size; i++) {
        mData[i] = value;
    }
}

template<class T>
Array<T>::Array(const Array& array) : mSize(array.mSize), mData(array.mData) {}

template<class T>
Array<T>::~Array() {
    delete[] mData;
}

template<class T>
Array<T>& Array<T>::operator=(const Array& other) {
    delete[] mData;
    mSize = other.mSize;
    mData = new T[mSize];
    std::copy(other.mData, other.mData + mSize, mData);
    return *this;
}

template<class T>
sz Array<T>::size() const {
    return mSize;
}

template<class T>
T& Array<T>::operator[] (sz index) {
    if (index >= mSize) {
        return mData[mSize - 1];
    }
    return mData[index];
}

template<class T>
const T& Array<T>::operator[] (sz index) const {
    if (index >= mSize) return mData[mSize - 1];
    return mData[index];
}