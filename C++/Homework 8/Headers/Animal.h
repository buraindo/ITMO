#pragma once

#include "Unit.h"

class Animal : virtual public Unit {
public:
	Animal(const sz& id, const sz& hp, const sz& dmg, bool friendly, const sz& intelligence);
	void enrage();
	void obey();
	sz getIntelligence() const;
	virtual std::string makeNoize() const;
	std::string getTypeName() const override;
protected:
	bool mFriendly;
	sz mIntelligence;
};