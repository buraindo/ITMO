#pragma once

#include <string>

typedef size_t sz;

class Unit {
public:
	Unit(const sz& id, const sz& hp, const sz& dmg);
	void heal(const sz& amount);
	void takeDamage(const sz& amount);
	void die();
	virtual std::string getTypeName() const;
	sz getId() const;
	sz getHealth() const;
	sz getDamage() const;
	bool isAlive() const;
protected:
	sz mId;
	sz mHealth;
	sz mDamage;
	bool mAlive;
};
