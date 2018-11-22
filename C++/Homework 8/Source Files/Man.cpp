#include "Man.h"

Man::Man(const sz & id, const sz & hp, const sz & dmg, const std::string & name, const sz & age, const sz & salary, const sz & gender) : 
	Unit(id, hp, dmg), mName(name), mAge(age), mSalary(salary), mGender(gender) {
	if (gender > FEMALE) {
		mGender = MALE;
	}
}

sz Man::getAge() const
{
	return mAge;
}

sz Man::getSalary() const
{
	return mSalary;
}

sz Man::getGender() const
{
	return mGender;
}

std::string Man::getName() const
{
	return mName;
}

void Man::grow()
{
	mAge++;
}

void Man::getRaise(const sz & amount)
{
	mSalary += amount;
}

void Man::loseJob()
{
	mSalary = sz(0);
}

void Man::getJob(const sz & salary)
{
	mSalary = salary;
}

std::string Man::getTypeName() const
{
	return "Man";
}
