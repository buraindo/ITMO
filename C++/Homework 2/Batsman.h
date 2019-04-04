#pragma once

#include <iostream>
#include <string>

using std::string;
using std::cin;
using std::cout;
using std::endl;

const int NAME_LENGTH = 20;
const int CODE_LENGTH = 4;

class Batsman {
public:
	void readData();

	void displayData();

private:
	string mCode;
	char mName[NAME_LENGTH + 1];
	int mInnings;
	int mNotOut;
	int mRuns;
	float mAverageValue;

	bool mIsGoodCode(const string& code);

	float mCalculateAverage();
};
