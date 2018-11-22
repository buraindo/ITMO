#pragma once

#include "Animal.h"

class Bear : public Animal {
public:
	Bear(const sz& id, const sz& hp, const sz& dmg, bool friendly, const sz& intelligence, const sz& power);
	void rage();
	void chill();
	sz getPower() const;
	void strengthen(const sz& amount);
	std::string getTypeName() const override;
	std::string makeNoize() const override;
private:
	bool mAngry;
	sz mPower;
};