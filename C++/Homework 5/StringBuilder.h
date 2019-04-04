#pragma once

#include <iostream>
#include <cstring>
#include <algorithm>

const char ASCII_NUL = '\0';

class StringBuilder {
public:
   explicit StringBuilder(const char* str);
   StringBuilder(const StringBuilder& other);
   StringBuilder(char symbol, size_t count);
   StringBuilder();
   ~StringBuilder();
   size_t getSize() const;
   void append(const StringBuilder& sb);
   void append(const char* s);
   void append(const int& i);
   void append(const double& d);
   void append(const char& c);
   StringBuilder substring(int start, int end) const;
   StringBuilder substring(int start) const;
   char charAt(int pos) const;
   void reverse();
   std::string toString() const;
   void print() const;
   void swap(StringBuilder& first, StringBuilder& second);
   StringBuilder& operator=(StringBuilder other);
private:
   char* mStr;
   size_t mSize;
};
