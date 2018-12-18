//
// Created by buraindo on 18.12.18.
//
#pragma once

#include <string>

#ifndef HW10_ORDER_H
#define HW10_ORDER_H

class Order {
public:
    Order() = default;
    Order(const std::string& name, int price, int time, int quantity);
    int getPrice();
    std::string getName();
    int getTimeToDeliver();
    int getQuantity();
    int getProperty(int metric);
    void setPrice(int price);
    void setName(const std::string& name);
    void setTimeToDeliver(int time);
    void setQuantity(int quantity);
private:
    std::string mName;
    int mPrice{};
    int mTimeToDeliver{};
    int mQuantity{};
};

#endif //HW10_ORDER_H
