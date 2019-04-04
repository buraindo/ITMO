#pragma once

#include "Animal.h"

class Pig : public Animal {
public:
	Pig(const sz& id, const sz& hp, const sz& dmg, bool friendly, const sz& intelligence, const sz& mass, bool latin);
	sz getMass() const;
	bool isLatin() const;
	void grow(const sz& amount);
	std::string getTypeName() const override;
	std::string makeNoize() const override;
private:
	sz mMass;
	bool mLatin;
};