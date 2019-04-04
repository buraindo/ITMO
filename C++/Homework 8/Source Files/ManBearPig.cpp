#include "ManBearPig.h"

ManBearPig::ManBearPig(const sz & id, const sz & hp, const sz & dmg, bool friendly, const sz & intelligence, 
	const sz & mass, bool latin, const sz & power, const std::string & name, const sz & age,
	const sz & salary, const sz & gender, const sz& type) : 
	Unit(id, hp, dmg), 
	Pig(id, hp, dmg, friendly, intelligence, mass, latin), Bear(id, hp, dmg, friendly, intelligence, power),
	Man(id, hp, dmg, name, age, salary, gender), 
	mCurrentType(type)
{}

void ManBearPig::transform(const sz & type)
{
	if (type > MAN) {
		mCurrentType = MAN;
	}
	else mCurrentType = type;
}

std::string ManBearPig::makeNoize() const
{
	if (mCurrentType == PIG) {
		return "REEEEEEEEEEEEEEEEEEEEEEEE";
	}
	if (mCurrentType == BEAR) {
		return "MWAAAAAAAAAAAAAAAAAAAAAAA";
	}
	if (mCurrentType == MAN) {
		return "cyka blyat";
	}
	return std::string();
}

std::string ManBearPig::getTypeName() const
{
	if (mCurrentType == PIG) {
		return "Pig";
	}
	if (mCurrentType == BEAR) {
		return "Bear";
	}
	if (mCurrentType == MAN) {
		return "Man";
	}
	return std::string();
}
