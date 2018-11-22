#pragma once

#include "Unit.h"

class Man : virtual public Unit {
public:
	Man(const sz& id, const sz& hp, const sz& dmg, const std::string& name, const sz& age, const sz& salary, const sz& gender);
	sz getAge() const;
	sz getSalary() const;
	sz getGender() const;
	std::string getName() const;
	void grow();
	void getRaise(const sz& amount);
	void loseJob();
	void getJob(const sz& salary);
	std::string getTypeName() const override;
private:
	const sz MALE = 0;
	const sz FEMALE = 1;
	std::string mName;
	sz mAge;
	sz mSalary;
	sz mGender;
};
