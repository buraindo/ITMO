#include "Pig.h"

Pig::Pig(const sz & id, const sz & hp, const sz & dmg, bool friendly, const sz & intelligence, const sz & mass, bool latin) : 
	Unit(id, hp, dmg), Animal(id, hp, dmg, friendly, intelligence), mMass(mass), mLatin(latin)
{}

sz Pig::getMass() const
{
	return mMass;
}

bool Pig::isLatin() const
{
	return mLatin;
}

void Pig::grow(const sz & amount)
{
	mMass += amount;
}

std::string Pig::getTypeName() const
{
	return "Pig";
}

std::string Pig::makeNoize() const
{
	return "REEEEEEEEEEEEEEEEEEEEEE";
}
