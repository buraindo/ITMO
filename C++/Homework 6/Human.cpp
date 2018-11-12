#include "Human.h"

Human::Human() : mCurrentAge(DEFAULT_AGE), mIsAlive(true), mName(DEFAULT_NAME), mLocation(DEFAULT_LOCATION), mLifeSpan(DEFAULT_LIFE_SPAN),
mHeight(DEFAULT_HEIGHT), mWeight(DEFAULT_WEIGHT), mGender(rand() % GENDER_NUMBER), mSalary(DEFAULT_SALARY) {
	mDefaultInit();
}

Human::Human(const std::string & name, const std::string & location, bool alive, int currentAge, int lifeSpan, int height, long long weight, int gender, long long salary, const std::vector<bool>& qualities) : mName(name), mLocation(location), mIsAlive(alive), mCurrentAge(currentAge), mLifeSpan(lifeSpan), mHeight(height), mWeight(weight), mGender(gender), mSalary(salary)
{
	mDefaultInit();
	mValidateData(currentAge, lifeSpan, gender, height, weight);
	for (int i = 0; i < (int)qualities.size(); i++) {
		mQualityMask[i] = qualities[i];
	}
}

Human::Human(const std::string & name, const std::string & location, int currentAge, int gender, long long salary, const std::vector<bool>& qualities) : mName(name), mLocation(location), mCurrentAge(currentAge), mLifeSpan(DEFAULT_LIFE_SPAN), mHeight(DEFAULT_ADULT_HEIGHT), mWeight(DEFAULT_ADULT_WEIGHT), mGender(gender), mSalary(salary)
{
	mDefaultInit();
	mValidateData(currentAge, DEFAULT_LIFE_SPAN, gender, DEFAULT_ADULT_HEIGHT, DEFAULT_ADULT_WEIGHT);
	for (int i = 0; i < (int)qualities.size(); i++) {
		mQualityMask[i] = qualities[i];
	}
}

Human::Human(const std::string & name, const std::string & location, int currentAge, int height, long long weight, int gender, long long salary, const std::vector<bool>& qualities) : mName(name), mLocation(location), mIsAlive(true), mCurrentAge(currentAge), mLifeSpan(DEFAULT_LIFE_SPAN), mHeight(height), mWeight(weight), mGender(gender), mSalary(salary)
{
	mDefaultInit();
	mValidateData(currentAge, currentAge, gender, height, weight);
	for (int i = 0; i < (int)qualities.size(); i++) {
		mQualityMask[i] = qualities[i];
	}
}

Human::Human(const Human & other) : mName(other.getName()), mLocation(other.getLocation()), mIsAlive(other.isAlive()), mCurrentAge(other.getAge()), mLifeSpan(other.mLifeSpan), mHeight(other.getHeight()), mWeight(other.getWeight()), mGender(mGenderToNum[other.getGender()]), mSalary(other.getSalary())
{
	mDefaultInit();
	for (int i = 0; i < (int)other.mQualityMask.size(); i++) {
		mQualityMask[i] = other.mQualityMask[i];
	}
}

Human & Human::operator=(const Human & other)
{
	mName = other.getName();
	mLocation = other.getLocation();
	mIsAlive = other.isAlive();
	mCurrentAge = other.getAge();
	mLifeSpan = other.mLifeSpan;
	mHeight = other.getHeight();
	mWeight = other.getWeight();
	mGender = mGenderToNum[other.getGender()];
	mSalary = other.getSalary();
	for (int i = 0; i < (int)other.mQualityMask.size(); i++) {
		mQualityMask[i] = other.mQualityMask[i];
	}
	return *this;
}

Human Human::operator+(const Human & other)
{
	//what can two people achieve together? longer name, better salary, etc..
	std::string longerName = other.getName().size() > mName.size() ? other.getName() : mName;
	std::string newLocation = other.getLocation() + ", " + mLocation;
	bool alive = mIsAlive || other.isAlive();
	int age = mCurrentAge + other.getAge();
	int lifeSpan = mLifeSpan + other.mLifeSpan;
	int height = std::max(mHeight, other.getHeight());
	long long weight = std::min(mWeight, other.getWeight());
	int gender = mGender | mGenderToNum[other.getGender()]; //lol feminists incoming
	long long salary = mSalary + other.getSalary();
	std::vector<bool> qualities(QUALITY_NUMBER);
	for (int i = 0; i < (int)other.mQualityMask.size(); i++) {
		qualities[i] = mQualityMask[i] || other.mQualityMask[i];
	}
	return Human(longerName, newLocation, alive, age, lifeSpan, height, weight, gender, salary, qualities);
}

Human Human::operator-(const Human & other)
{
	std::string shorterName = other.getName().size() < mName.size() ? other.getName() : mName;
	std::string newLocation = DEFAULT_LOCATION;
	bool alive = mIsAlive & other.isAlive();
	int age = mCurrentAge - other.getAge();
	int lifeSpan = mLifeSpan - other.mLifeSpan;
	int height = std::min(mHeight, other.getHeight());
	long long weight = std::max(mWeight, other.getWeight());
	int gender = mGender & mGenderToNum[other.getGender()]; //now ok
	long long salary = mSalary - other.getSalary();
	std::vector<bool> qualities(QUALITY_NUMBER);
	for (int i = 0; i < (int)other.mQualityMask.size(); i++) {
		qualities[i] = mQualityMask[i] & other.mQualityMask[i];
	}
	return Human(shorterName, newLocation, alive, age, lifeSpan, height, weight, gender, salary, qualities);
}

Human Human::operator*(const Human & other)
{
	std::string newName = mName + other.getName();
	std::string newLocation = mLocation + other.getLocation();
	bool alive = mIsAlive || other.isAlive();
	int age = mCurrentAge * other.getAge();
	int lifeSpan = mLifeSpan * other.mLifeSpan;
	int height = std::max(mHeight, other.getHeight());
	long long weight = std::min(mWeight, other.getWeight());
	int gender = mGender | mGenderToNum[other.getGender()];
	long long salary = mSalary * other.getSalary();
	std::vector<bool> qualities(QUALITY_NUMBER);
	for (int i = 0; i < (int)other.mQualityMask.size(); i++) {
		qualities[i] = mQualityMask[i] || other.mQualityMask[i];
	}
	return Human(newName, newLocation, alive, age, lifeSpan, height, weight, gender, salary, qualities);
}

Human Human::operator/(const Human & other)
{
	std::string newName = DEFAULT_NAME;
	std::string newLocation = DEFAULT_LOCATION;
	bool alive = mIsAlive && other.isAlive();
	int age = mCurrentAge / other.getAge();
	int lifeSpan = mLifeSpan / other.mLifeSpan;
	int height = std::min(mHeight, other.getHeight());
	long long weight = std::max(mWeight, other.getWeight());
	int gender = mGender & mGenderToNum[other.getGender()];
	long long salary = mSalary / other.getSalary();
	std::vector<bool> qualities(QUALITY_NUMBER);
	for (int i = 0; i < (int)other.mQualityMask.size(); i++) {
		qualities[i] = mQualityMask[i] && other.mQualityMask[i];
	}
	return Human(newName, newLocation, alive, age, lifeSpan, height, weight, gender, salary, qualities);
}

Human Human::operator%(const Human & other)
{
	std::string newName = DEFAULT_NAME;
	std::string newLocation = DEFAULT_LOCATION;
	bool alive = mIsAlive && other.isAlive();
	int age = mCurrentAge % other.getAge();
	int lifeSpan = mLifeSpan % other.mLifeSpan;
	int height = std::min(mHeight, other.getHeight());
	long long weight = std::max(mWeight, other.getWeight());
	int gender = mGender & mGenderToNum[other.getGender()];
	long long salary = mSalary % other.getSalary();
	std::vector<bool> qualities(QUALITY_NUMBER);
	for (int i = 0; i < (int)other.mQualityMask.size(); i++) {
		qualities[i] = mQualityMask[i] && other.mQualityMask[i];
	}
	return Human(newName, newLocation, alive, age, lifeSpan, height, weight, gender, salary, qualities);
}

Human Human::operator&(const Human & other)
{
	bool alive = mIsAlive && other.isAlive();
	int gender = mGender & mGenderToNum[other.getGender()];
	std::vector<bool> qualities(QUALITY_NUMBER);
	for (int i = 0; i < (int)other.mQualityMask.size(); i++) {
		qualities[i] = mQualityMask[i] && other.mQualityMask[i];
	}
	return Human(mName, mLocation, alive, mCurrentAge, mLifeSpan, mHeight, mWeight, gender, mSalary, qualities);
}

Human Human::operator|(const Human & other)
{
	bool alive = mIsAlive || other.isAlive();
	int gender = mGender | mGenderToNum[other.getGender()];
	std::vector<bool> qualities(QUALITY_NUMBER);
	for (int i = 0; i < (int)other.mQualityMask.size(); i++) {
		qualities[i] = mQualityMask[i] || other.mQualityMask[i];
	}
	return Human(mName, mLocation, alive, mCurrentAge, mLifeSpan, mHeight, mWeight, gender, mSalary, qualities);
}

Human Human::operator^(const Human & other)
{
	bool alive = mIsAlive ^ other.isAlive();
	int gender = mGender ^ mGenderToNum[other.getGender()];
	std::vector<bool> qualities(QUALITY_NUMBER);
	for (int i = 0; i < (int)other.mQualityMask.size(); i++) {
		qualities[i] = mQualityMask[i] ^ other.mQualityMask[i];
	}
	return Human(mName, mLocation, alive, mCurrentAge, mLifeSpan, mHeight, mWeight, gender, mSalary, qualities);
}

Human & Human::operator+=(const Human & other)
{
	*this = *this + other;
	return *this;
}

Human & Human::operator-=(const Human & other)
{
	*this = *this - other;
	return *this;
}

Human & Human::operator*=(const Human & other)
{
	*this = *this * other;
	return *this;
}

Human & Human::operator/=(const Human & other)
{
	*this = *this / other;
	return *this;
}

Human & Human::operator%=(const Human & other)
{
	*this = *this % other;
	return *this;
}

Human & Human::operator&=(const Human & other)
{
	*this = *this & other;
	return *this;
}

Human & Human::operator|=(const Human & other)
{
	*this = *this | other;
	return *this;
}

Human & Human::operator^=(const Human & other)
{
	*this = *this ^ other;
	return *this;
}

Human Human::operator~()
{
	bool alive = !mIsAlive;
	int gender = ~mGender;
	std::vector<bool> qualities(QUALITY_NUMBER);
	for (int i = 0; i < (int)mQualityMask.size(); i++) {
		qualities[i] = !mQualityMask[i];
	}
	return Human(mName, mLocation, alive, mCurrentAge, mLifeSpan, mHeight, mWeight, gender, mSalary, qualities);
}

Human Human::operator+()
{
	Human human(*this);
	human.mReincarnate();
	return human;
}

Human Human::operator-()
{
	//-chelik
	Human human(*this);
	human.mDie();
	return human;
}

Human & Human::operator++()
{
	//time flies
	this->mCurrentAge++;
	this->mValidateData(mCurrentAge, mLifeSpan, mGender, mHeight, mWeight);
	return *this;
}

Human & Human::operator--()
{
	this->mCurrentAge--;
	this->mValidateData(mCurrentAge, mLifeSpan, mGender, mHeight, mWeight);
	return *this;
}

Human Human::operator++(int)
{
	Human human = *this;
	++human;
	return human;
}

Human Human::operator--(int)
{
	Human human = *this;
	--human;
	return human;
}

bool Human::operator[](const std::string & property) const
{
	if (mQualityToNum.find(property) == mQualityToNum.end()) {
		std::cout << "No such quality" << std::endl;
		return false;
	}
	int index = mQualityToNum.at(property);
	if (index > 7) {
		return !mQualityMask[index - 7];
	}
	return mQualityMask[index];
}

bool Human::operator!() const
{
	return !mIsAlive;
}

bool Human::operator==(const Human & other) const
{
	//all people are unique!
	return false;
}

bool Human::operator!=(const Human & other) const
{
	//all people are unique!
	return true;
}

bool Human::operator<(const Human & other) const
{
	int leftGoodCount = 0;
	int rightGoodCount = 0;
	for (int i = 0; i < (int)mQualityMask.size(); i++) {
		if (mQualityMask[i]) leftGoodCount++;
		if (other.mQualityMask[i]) rightGoodCount++;
	}
	return leftGoodCount < rightGoodCount;
}

bool Human::operator>(const Human & other) const
{
	int leftGoodCount = 0;
	int rightGoodCount = 0;
	for (int i = 0; i < (int)mQualityMask.size(); i++) {
		if (mQualityMask[i]) leftGoodCount++;
		if (other.mQualityMask[i]) rightGoodCount++;
	}
	return leftGoodCount > rightGoodCount;
}

bool Human::operator<=(const Human & other) const
{
	return *this < other;
}

bool Human::operator>=(const Human & other) const
{
	return *this > other;
}

bool Human::operator&&(const Human & other) const
{
	return other.isAlive() && mIsAlive;
}

bool Human::operator||(const Human & other) const
{
	return other.isAlive() || mIsAlive;
}

std::string Human::getName() const
{
	return mName;
}

std::string Human::getLocation() const
{
	return mLocation;
}

std::string Human::getGender() const
{
	return mNumToGender.at(mGender);
}

int Human::getAge() const
{
	return mCurrentAge;
}

int Human::getHeight() const
{
	return mHeight;
}

int Human::timeLeft() const
{
	return mLifeSpan - mCurrentAge;
}

long long Human::getWeight() const
{
	return mWeight;
}

long long Human::getSalary() const
{
	return mSalary;
}

bool Human::isFat() const
{
	return mWeight > FAT_LIMIT;
}

bool Human::isAlive() const
{
	return mIsAlive;
}

bool Human::isDead() const
{
	return !mIsAlive;
}

bool Human::isTall() const
{
	return mHeight > TALL_LIMIT;
}

bool Human::isSuccessful() const
{
	return mSalary > SUCCESS_LIMIT;
}

bool Human::isGood() const
{
	int goodCount = 0;
	for (int i = 0; i < (int)mQualityMask.size(); i++) {
		if (mQualityMask[i]) goodCount++;
	}
	return goodCount > QUALITY_NUMBER / 2;
}

bool Human::isBad() const
{
	return !isGood();
}

void Human::setName(const std::string & name)
{
	mName = name;
}

void Human::setLocation(const std::string & location)
{
	mLocation = location;
}

void Human::setAge(int age)
{
	mCurrentAge = age;
}

void Human::setLifeSpan(int lifespan)
{
	mLifeSpan = lifespan;
}

void Human::setAlive(bool alive)
{
	mIsAlive = alive;
}

void Human::setHeight(int height)
{
	mHeight = height;
}

void Human::setWeight(long long weight)
{
	mWeight = weight;
}

void Human::setGender(int gender)
{
	mGender = gender;
}

void Human::setSalary(long long salary)
{
	mSalary = salary;
}

void Human::setQualities(const std::vector<bool>& qualities)
{
	for (int i = 0; i < (int)qualities.size(); i++) {
		mQualityMask[i] = qualities[i];
	}
}

void Human::become(const std::string quality)
{
	if (mQualityToNum.find(quality) == mQualityToNum.end()) {
		std::cout << "No such quality" << std::endl;
		return;
	}
	int index = mQualityToNum.at(quality);
	if (index > 7) {
		mQualityMask[index - 7] = false;
	}
	else {
		mQualityMask[index] = true;
	}
}

void Human::grow(int plusHeight)
{
	mHeight += plusHeight;
}

void Human::shorten(int minusHeight)
{
	mHeight -= minusHeight;
}

void Human::plump(long long plusWeight)
{
	mWeight += plusWeight;
}

void Human::thinOut(long long minusWeight)
{
	mWeight -= minusWeight;
}

void Human::raise(long long plusSalary)
{
	mSalary += plusSalary;
}

void Human::decrease(long long minusSalary)
{
	mSalary -= minusSalary;
}

void Human::getFired()
{
	mSalary = 0;
}

void Human::getNewJob(long long salary)
{
	mSalary = salary;
}

void Human::getNewJob(const std::string & location, long long salary)
{
	mLocation = location;
	mSalary = salary;
}

void Human::mDefaultInit()
{
	mQualityMask.resize(QUALITY_NUMBER);
	mGenders.push_back("Female");
	mGenders.push_back("Male");
	mQualities.push_back("Intelligent");
	mQualities.push_back("Happy");
	mQualities.push_back("Generous");
	mQualities.push_back("Honest");
	mQualities.push_back("Creative");
	mQualities.push_back("Lucky");
	mQualities.push_back("Fun");
	mQualities.push_back("Dumb");
	mQualities.push_back("Unhappy");
	mQualities.push_back("Greedy");
	mQualities.push_back("Dishonest");
	mQualities.push_back("Unproductive");
	mQualities.push_back("Unlucky");
	mQualities.push_back("Dull");
	for (int i = 0; i < (int)mGenders.size(); i++) {
		std::string gender = mGenders[i];
		mGenderToNum[gender] = i;
		mNumToGender[i] = gender;
	}
	for (int i = 0; i < (int)mQualities.size(); i++) {
		std::string quality = mQualities[i];
		mQualityToNum[quality] = i;
		mNumToQuality[i] = quality;
	}
}

void Human::mDie()
{
	mIsAlive = false;
}

void Human::mReincarnate()
{
	mIsAlive = true;
}

void Human::mValidateData(int currentAge, int lifeSpan, int gender, int height, long long weight)
{
	if (currentAge < 0 || currentAge < lifeSpan) {
		mIsAlive = false;
	}
	if (gender > 1) {
		std::cout << "We ain't some sort of freaks or something so get ready to have a random gender." << std::endl;
		mGender = rand() % 2;
	}
	if (height < 0) {
		height = DEFAULT_ADULT_HEIGHT;
	}
	if (weight < 0) {
		DEFAULT_ADULT_WEIGHT;
	}
}

std::istream & operator>>(std::istream & reader, Human & other)
{
	std::cout << "Name: " << std::endl;
	std::string name;
	reader >> name;
	std::cout << "Location: " << std::endl;
	std::string location;
	reader >> location;
	std::cout << "Age: " << std::endl;
	int age;
	reader >> age;
	std::cout << "Gender: " << std::endl;
	int gender;
	reader >> gender;
	std::cout << "Salary: " << std::endl;
	long long salary;
	reader >> salary;
	std::cout << "Are they intelligent?" << std::endl;
	bool intelligent;
	reader >> intelligent;
	std::cout << "Are they happy?" << std::endl;
	bool happy;
	reader >> happy;
	std::cout << "Are they generous?" << std::endl;
	bool generous;
	reader >> generous;
	std::cout << "Are they honest?" << std::endl;
	bool honest;
	reader >> honest;
	std::cout << "Are they creative?" << std::endl;
	bool creative;
	reader >> creative;
	std::cout << "Are they lucky?" << std::endl;
	bool lucky;
	reader >> lucky;
	std::cout << "Are they fun?" << std::endl;
	bool fun;
	reader >> fun;
	std::cout << "Type Y if you wanna continue filling information, N if not: " << std::endl;
	char ans;
	reader >> ans;
	while (ans != 'Y' && ans != 'N') {
		std::cout << "Type Y if you wanna continue filling information, N if not: " << std::endl;
		reader >> ans;
	}
	std::vector<bool> qualities{ intelligent, happy, generous, honest, creative, lucky, fun };
	if (ans == 'N') {
		other = Human(name, location, age, gender, salary, qualities);
		return reader;
	}
	std::cout << "Height: " << std::endl;
	int height;
	reader >> height;
	std::cout << "Weight: " << std::endl;
	long long weight;
	reader >> weight;
	other = Human(name, location, age, height, weight, gender, salary, qualities);
	return reader;
}

std::ostream & operator<<(std::ostream & writer, Human & other)
{
	writer << "Name: " << other.getName() << std::endl;
	writer << "Location: " << other.getLocation() << std::endl;
	writer << "Age: " << other.getAge() << std::endl;
	writer << "Gender: " << other.getGender() << std::endl;
	writer << "Height: " << other.getHeight() << std::endl;
	writer << "Weight: " << other.getWeight() << std::endl;
	writer << "Salary: " << other.getSalary() << std::endl;
	std::vector<int> good, bad;
	for (int i = 0; i < (int)other.mQualityMask.size(); i++) {
		if (other.mQualityMask[i]) good.push_back(i);
		else bad.push_back(i + 7);
	}
	if (good.size() > 0) {
		writer << "This person is ";
		for (int i = 0; i < (int)good.size() - 1; i++) {
			writer << other.mNumToQuality[good[i]] << ", ";
		}
		writer << other.mNumToQuality[good[good.size() - 1]] << std::endl;
	}
	if (bad.size() > 0) {
		writer << "But also ";
		for (int i = 0; i < (int)bad.size() - 1; i++) {
			writer << other.mNumToQuality[bad[i]] << ", ";
		}
		writer << other.mNumToQuality[bad[bad.size() - 1]] << std::endl;
	}
	return writer;
}
