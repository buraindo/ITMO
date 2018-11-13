//
// Created by buraindo on 13.11.18.
//
#pragma once

#include <cstdio>

#ifndef GENERIC_ARRAY_H
#define GENERIC_ARRAY_H

typedef size_t sz;

template<class T>
class Array {
public:
    explicit Array(sz size = 0, const T& value = T());
    Array(const Array& array);
    ~Array();
    Array& operator=(const Array& other);
    sz size() const;
    T& operator[] (sz index);
    const T& operator[](sz index) const;
private:
    T* mData;
    sz mSize;
};

#endif

#include "Array.hpp"
