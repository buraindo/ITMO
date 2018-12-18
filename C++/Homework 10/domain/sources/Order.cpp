//
// Created by buraindo on 18.12.18.
//
#include <Order.h>
#include <Util.h>

Order::Order(const std::string& name, int price, int time, int quantity) : mName(name), mPrice(price),
mTimeToDeliver(time), mQuantity(quantity) {}

int Order::getProperty(int metric) {
    switch (metric) {
        case Util::PRICE:
            return getPrice();
        case Util::TIME:
            return getTimeToDeliver();
        case Util::QUANTITY:
            return getQuantity();
        default:
            break;
    }
    return 0;
}

int Order::getPrice() {
    return mPrice;
}

int Order::getQuantity() {
    return mQuantity;
}

int Order::getTimeToDeliver() {
    return mTimeToDeliver;
}

std::string Order::getName() {
    return mName;
}

void Order::setQuantity(int quantity) {
    mQuantity = quantity;
}

void Order::setTimeToDeliver(int time) {
    mTimeToDeliver = time;
}

void Order::setPrice(int price) {
    mPrice = price;
}

void Order::setName(const std::string& name) {
    mName = name;
}
