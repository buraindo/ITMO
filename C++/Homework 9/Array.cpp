#pragma clang diagnostic push
#pragma ide diagnostic ignored "performance-noexcept-move-constructor"
//
// Created by buraindo on 13.12.18.
//

#pragma once

#include "Array.hpp"
#include <algorithm>
#include <utility>

template<class Type>
Array<Type>::Array(size_t arrSize, const Type& value) : mSize(arrSize), mData(new Type[arrSize])
{
    for (size_t i = 0; i < arrSize; ++i)
    {
        mData[i] = value;
    }
}

template<class Type>
void Array<Type>::swap(Array& next)
{
    std::swap(mData, next.mData);
    std::swap(mSize, next.mSize);
}

template<class Type>
Array<Type>::Array(const Array& next)
{
    mSize = next.size();
}

template<class Type>
Array<Type>::Array(Array&& next)
{
    swap(next);
    next.mData = nullptr;
}

template<class Type>
Array<Type>::~Array()
{
    delete[] mData;
}

template<class Type>
Array<Type>& Array<Type>::operator=(const Array& next)
{
    delete[] mData;
    mSize = next.size();
    mData = new Type[mSize];
    std::copy(next.mData, next.mData + mSize, mData);
    return *this;
}

template<class Type>
Array<Type>& Array<Type>::operator=(Array&& next)
{
    swap(next);
    next.mData = nullptr;
    return *this;
}

template<class Type>
size_t Array<Type>::size() const
{
    return mSize;
}

template<class Type>
Type& Array<Type>::operator[](size_t index)
{
    if (index >= mSize)
    {
        return mData[mSize - 1];
    }
    return mData[index];
}

template<class Type>
const Type& Array<Type>::operator[](size_t index) const
{
    if (index >= mSize)
    {
        return mData[mSize - 1];
    }
    return mData[index];
}

#pragma clang diagnostic pop