#include "Unit.h"

Unit::Unit(const sz & id, const sz & hp, const sz & dmg) : mId(id), mHealth(hp), mDamage(dmg)
{
	mAlive = mHealth > 0;
}

void Unit::heal(const sz & amount)
{
	if (mAlive) {
		mHealth += amount;
	}
}

void Unit::takeDamage(const sz & amount)
{
	if (amount > mHealth) {
		die();
	}
	else {
		mHealth -= amount;
	}
}

void Unit::die()
{
	mHealth = 0;
	mAlive = false;
}

std::string Unit::getTypeName() const
{
	return "Unit";
}

sz Unit::getId() const
{
	return mId;
}

sz Unit::getHealth() const
{
	return mHealth;
}

sz Unit::getDamage() const
{
	return mDamage;
}

bool Unit::isAlive() const
{
	return mAlive;
}
