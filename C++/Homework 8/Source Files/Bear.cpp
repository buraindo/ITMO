#include "Bear.h"

Bear::Bear(const sz & id, const sz & hp, const sz & dmg, bool friendly, const sz & intelligence, const sz & power) : 
	Unit(id, hp, dmg), Animal(id, hp, dmg, friendly, intelligence), mPower(power) {
	if (power == 0) {
		mPower = 1;
	}
}

void Bear::rage()
{
	if (!mAngry) {
		mAngry = true;
		mDamage *= mPower;
	}
}

void Bear::chill()
{
	if (mAngry) {
		mAngry = false;
		mDamage /= mPower;
	}
}

sz Bear::getPower() const
{
	return mPower;
}

void Bear::strengthen(const sz& amount)
{
	mPower += amount;
}

std::string Bear::getTypeName() const
{
	return "Bear";
}

std::string Bear::makeNoize() const
{
	return "MWAAAAAAAAAA";
}
