#pragma once

#include "Bear.h"
#include "Pig.h"
#include "Man.h"

class ManBearPig : public Man, public Bear, public Pig {
public:
	ManBearPig(const sz& id, const sz& hp, const sz& dmg, bool friendly, const sz& intelligence, const sz& mass, bool latin, const sz& power, const std::string& name, const sz& age, const sz& salary, const sz& gender, const sz& type);
	void transform(const sz& type);
	std::string makeNoize() const override;
	std::string getTypeName() const override;
private:
	const sz PIG = 0;
	const sz BEAR = 1;
	const sz MAN = 2;
	sz mCurrentType;
};
