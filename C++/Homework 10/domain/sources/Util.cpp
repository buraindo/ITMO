//
// Created by buraindo on 18.12.18.
//
#include <Util.h>

void Util::init() {
    metricToInt["price"] = 0;
    metricToInt["time"] = 1;
    metricToInt["quantity"] = 2;
    algorithmToInt["random"] = 0;
    algorithmToInt["sort"] = 1;
    algorithmToInt["average"] = 2;
}

std::map<std::string, int> Util::metricToInt;
std::map<std::string, int> Util::algorithmToInt;

