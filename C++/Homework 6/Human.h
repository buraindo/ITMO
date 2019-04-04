//qualities
/*
intelligent, happy,   generous, honest,    creative, 	   lucky,    fun
dumb,		 unhappy, greedy,   dishonest, unproductive,   unlucky,  dull
*/
#pragma once

#include <iostream>
#include <vector>
#include <string>
#include <map>
#include <algorithm>

class Human {
public:
	Human();
	Human(const std::string& name, const std::string& location, bool alive, int currentAge, int lifeSpan, int height, long long weight, int gender, long long salary, const std::vector<bool>& qualities);
	Human(const std::string& name, const std::string& location, int currentAge, int gender, long long salary, const std::vector<bool>& qualities);
	Human(const std::string& name, const std::string& location, int currentAge, int height, long long weight, int gender, long long salary, const std::vector<bool>& qualities);
	Human(const Human& other);

	Human& operator=(const Human& other);
	Human operator+(const Human& other);
	Human operator-(const Human& other);
	Human operator*(const Human& other);
	Human operator/(const Human& other);
	Human operator%(const Human& other);
	Human operator&(const Human& other);
	Human operator|(const Human& other);
	Human operator^(const Human& other);
	Human& operator+=(const Human& other);
	Human& operator-=(const Human& other);
	Human& operator*=(const Human& other);
	Human& operator/=(const Human& other);
	Human& operator%=(const Human& other);
	Human& operator&=(const Human& other);
	Human& operator|=(const Human& other);
	Human& operator^=(const Human& other);
	Human operator~();
	Human operator+();
	Human operator-();
	Human& operator++();
	Human& operator--();
	Human operator++(int);
	Human operator--(int);
	bool operator[](const std::string& property) const;
	bool operator!() const;
	bool operator==(const Human& other) const;
	bool operator!=(const Human& other) const;
	bool operator<(const Human& other) const;
	bool operator>(const Human& other) const;
	bool operator<=(const Human& other) const;
	bool operator>=(const Human& other) const;
	bool operator&&(const Human& other) const;
	bool operator||(const Human& other) const;
	friend std::istream& operator>>(std::istream& reader, Human& other);
	friend std::ostream& operator<<(std::ostream& writer, Human& other);

	std::string getName() const;
	std::string getLocation() const;
	std::string getGender() const;
	int getAge() const;
	int getHeight() const;
	int timeLeft() const;
	long long getWeight() const;
	long long getSalary() const;
	bool isFat() const;
	bool isAlive() const;
	bool isDead() const;
	bool isTall() const;
	bool isSuccessful() const;
	bool isGood() const;
	bool isBad() const;

	void setName(const std::string& name);
	void setLocation(const std::string& location);
	void setAge(int age);
	void setLifeSpan(int lifespan);
	void setAlive(bool alive);
	void setHeight(int height);
	void setWeight(long long weight);
	void setGender(int gender); //yeah, yeah, in 2018 everything can work
	void setSalary(long long salary);
	void setQualities(const std::vector<bool>& qualities);
	void become(const std::string quality);
	void grow(int plusHeight);
	void shorten(int minusHeight);
	void plump(long long plusWeight);
	void thinOut(long long minusWeight);
	void raise(long long plusSalary);
	void decrease(long long minusSalary);
	void getFired();
	void getNewJob(long long salary);
	void getNewJob(const std::string& location, long long salary);

	const static int FEMALE = 0;
	const static int MALE = 1;
private:
	const std::string DEFAULT_NAME = "Sych";
	const std::string DEFAULT_LOCATION = "Mukhosransk";
	const int DEFAULT_AGE = 0;
	const int DEFAULT_LIFE_SPAN = 70;
	const int DEFAULT_HEIGHT = 53;
	const long long DEFAULT_WEIGHT = 4;
	const int DEFAULT_ADULT_HEIGHT = 175;
	const long long DEFAULT_ADULT_WEIGHT = 60;
	const long long DEFAULT_SALARY = 0;
	const int QUALITY_NUMBER = 7; //seven deadly sins
	const int GENDER_NUMBER = 2; //I lied AHAHHAHAHAHAHAHAHAH
	const long long FAT_LIMIT = 80;
	const int TALL_LIMIT = 180;
	const long long SUCCESS_LIMIT = 100000;

	std::map<int, std::string> mNumToGender;
	std::map<int, std::string> mNumToQuality;
	std::map<std::string, int> mGenderToNum;
	std::map<std::string, int> mQualityToNum;
	std::vector<std::string> mGenders;
	std::vector<std::string> mQualities;

	std::string mName;
	std::string mLocation;
	bool mIsAlive;
	int mCurrentAge;
	int mLifeSpan;
	int mHeight;
	long long mWeight;
	int mGender; //int because we have MORE than 2 genders (hello SJW)
	long long mSalary;
	std::vector<bool> mQualityMask;

	void mDefaultInit();
	void mDie();
	void mReincarnate();
	void mValidateData(int currentAge, int lifeSpan, int gender, int height, long long weight);
};