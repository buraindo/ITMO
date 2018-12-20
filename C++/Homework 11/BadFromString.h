//
// Created by buraindo on 20.12.18.
//

#ifndef HW11_BADFROMSTRING_H
#define HW11_BADFROMSTRING_H

#include <string>
#include <exception>

class BadFromString : std::exception {
public:
    explicit BadFromString(const std::string& message);
    const char* what() const noexcept override;
private:
    std::string mMessage;
};


#endif //HW11_BADFROMSTRING_H
