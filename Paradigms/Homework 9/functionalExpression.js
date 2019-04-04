var types = {'x': 0, 'y': 1, 'z': 2};

function binaryOperation(delegate) {
    return function (lhs, rhs) {
        return function (x, y, z) {
            return delegate(lhs(x, y, z), rhs(x, y, z));
        };
    }
}

function unaryOperation(delegate) {
    return function (num) {
        return function (x, y, z) {
            return delegate(num(x, y, z));
        };
    }
}

var add = binaryOperation(function (lhs, rhs) {
    return lhs + rhs;
});
var subtract = binaryOperation(function (lhs, rhs) {
    return lhs - rhs;
});
var multiply = binaryOperation(function (lhs, rhs) {
    return lhs * rhs;
});
var divide = binaryOperation(function (lhs, rhs) {
    return lhs / rhs;
});

function cnst(num) {
    return function() {
		return num; 
	}
}

function variable(type) {
    return function () {
        return arguments[types[type]];
    }
}

var negate = unaryOperation(function (num) {
    return -num;
});
var cuberoot = unaryOperation(function (num) {
    return Math.pow(num, 1 / 3);
});
var cube = unaryOperation(function (num) {
    return Math.pow(num, 3);
});