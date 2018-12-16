#pragma clang diagnostic push
#pragma ide diagnostic ignored "performance-noexcept-move-constructor"
//
// Created by buraindo on 13.12.18.
//
#pragma once

#include <cstdio>

template<class Type>
class Array
{
public:
    explicit Array(size_t arrSize = 0, const Type& value = Type());
    Array(Array const& arr);
    Array(Array&& arr);
    ~Array();
    Array& operator=(const Array& next);
    Array& operator=(Array&& next);
    size_t size() const;
    Type& operator[] (size_t index);
    const Type& operator[](size_t index) const;
private:
    void swap(Array& next);
    Type* mData;
    size_t mSize;
};

#include "Array.cpp"

#pragma clang diagnostic pop