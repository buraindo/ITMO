#include "Animal.h"

Animal::Animal(const sz & id, const sz & hp, const sz & dmg, bool friendly, const sz & intelligence) :
	Unit(id, hp, dmg), mFriendly(friendly), mIntelligence(intelligence)
{}

void Animal::enrage()
{
	mFriendly = false;
}

void Animal::obey()
{
	mFriendly = true;
}

sz Animal::getIntelligence() const
{
	return mIntelligence;
}

std::string Animal::makeNoize() const
{
	return "Ermm, abstract noize, I guess";
}

std::string Animal::getTypeName() const
{
	return "Animal";
}
