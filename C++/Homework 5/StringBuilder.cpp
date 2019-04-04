#include "StringBuilder.h"

StringBuilder::StringBuilder(const char* str) {
   mSize = strlen(str);
   mStr = new char[mSize + 1];
   strcpy(mStr, str);
}

StringBuilder::StringBuilder(char symbol, size_t count) {
   std::string s(count, symbol);
   mSize = s.length();
   mStr = new char[mSize + 1];
   strcpy(mStr, s.c_str());
   mStr[s.length()] = ASCII_NUL;
}

StringBuilder::StringBuilder() {
	mSize = 0;
	mStr = new char[1];
	mStr[0] = ASCII_NUL;
}

StringBuilder::~StringBuilder() {
	delete [] mStr;
}

StringBuilder::StringBuilder(const StringBuilder& other) {
	mSize = other.getSize();
	mStr = new char[mSize + 1];
	strcpy(mStr, other.toString().c_str());
	mStr[mSize] = ASCII_NUL;
}

void StringBuilder::swap(StringBuilder& first, StringBuilder& second)
{
	std::swap(first.mSize, second.mSize);
	std::swap(first.mStr, second.mStr);
}

StringBuilder& StringBuilder::operator=(StringBuilder other) {
	swap(*this, other);
	return *this;
}

std::string StringBuilder::toString() const {
	return std::string(mStr);
}

size_t StringBuilder::getSize() const {
	return mSize;
}

void StringBuilder::append(const StringBuilder& sb) {
	append(sb.toString().c_str());
}

void StringBuilder::append(const char* s) {
	std::string str(s);
	size_t extraSize = strlen(s);
	size_t previousSize = mSize;
	char* previous = new char[previousSize + 1];
	strcpy(previous, mStr);
	delete [] mStr;
	mStr = new char[previousSize + extraSize + 1];
	std::copy(previous, previous + previousSize, mStr);
	std::copy(str.begin(), str.end(), mStr + previousSize);
	mSize = previousSize + extraSize;
	mStr[mSize] = ASCII_NUL;
	delete [] previous;
}

void StringBuilder::append(const int& i) {
	append(std::to_string(i).c_str());
}

void StringBuilder::append(const char& c) {
	append(std::string(1, c).c_str());
}

void StringBuilder::append(const double& d) {
	append(std::to_string(d).c_str());
}

void StringBuilder::print() const {
	for (int i = 0; i < (int) mSize + 1; ++i) {
		std::cout << static_cast<int>(mStr[i]) << " ";
	}
	std::cout << std::endl;
}

StringBuilder StringBuilder::substring(int start, int end) const {
	int length = end - start + 1;
	StringBuilder ans('a', length);
	for (int i = start; i <= end; i++) {
		ans.mStr[i - start] = mStr[i];
	}
	return ans;
}

StringBuilder StringBuilder::substring(int start) const {
	return substring(start, (int) mSize);
}

char StringBuilder::charAt(int pos) const {
	return mStr[pos];
}

void StringBuilder::reverse() {
	std::reverse(mStr, mStr + mSize);
	mStr[mSize] = ASCII_NUL;
}
