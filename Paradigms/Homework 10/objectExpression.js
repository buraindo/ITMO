'use strict';

var types = {'x': 0, 'y': 1, 'z': 2};

function require(pack) {
    for (var item in pack) {
        global[item] = pack[item];
    }
}

var exceptions = (function () {

    function draw(pos, len) {
        var res = '\n';
        for (var i = 0; i < pos; i++) {
            res += ' ';
        }
        res += '^';
        for (var i = 0; i < len - 1; i++) {
            res += '~';
        }
        return res;
    }

    function ProtoException(message) {
        this.message = message
    }

    ProtoException.prototype = Error.prototype;
    ProtoException.prototype.name = 'ProtoException';

    function Exception(name) {
        this.name = name
    }

    Exception.prototype = ProtoException.prototype;

    function createException(name, delegate) {
        var result = function () {
            ProtoException.call(this, delegate.apply(null, arguments));
        };
        result.prototype = new Exception(name);
        return result;
    }

    var OddClosingParenthesis = createException(
        'OddClosingParenthesis',
        function (expr, ind) {
            return ('Odd closing parenthesis at position: ' + ind + '\n' + expr + draw(ind, 1));
        }
    );

    var MissingClosingParenthesis = createException(
        'MissingClosingParenthesis',
        function (expr, ind) {
            return ('Expected closing parenthesis before position: ' + ind + '\n' + expr + draw(ind, 1));
        }
    );

    var MissingOperationParenthesis = createException(
        'MissingOperationParenthesis',
        function (expr, op, ind) {
            var ending = op + " at position: " + ind + '\n' + expr + draw(ind, op.length);
            return ("Expected closing parenthesis after operation '" + ending);
        }
    );

    var MissingOperand = createException(
        'MissingOperand',
        function (expr, op, ind) {
            return ("Too few operands for operation '" + op + "' at position: " + ind + '\n' + expr + draw(ind, op.length));
        }
    );

    var OddOperand = createException(
        'OddOperand',
        function (expr, op, ind) {
            return ("Too many operands for operation '" + op + "' at position: " + ind + '\n' + expr + draw(ind, op.length));
        }
    );

    var UnknownIdentifier = createException(
        'UnknownIdentifier',
        function (expr, id, ind) {
            return ("Unknown identifier '" + id + "' at position: " + ind + '\n' + expr + draw(ind, id.length));
        }
    );

    var UnknownSymbol = createException(
        'UnknownSymbol',
        function (expr, ind) {
            return ("Unknown symbol '" + expr.charAt(ind) + "' at position: " + ind + '\n' + expr + draw(ind, 1));
        }
    );

    var MissingOperation = createException(
        'MissingOperation',
        function (expr, ind) {
            var reason = 'Expected operation after opening parenthesis at position ';
            return (reason + ind + '\n' + expr + draw(ind, 1));
        }
    );

    var OddSuffix = createException(
        'OddSuffix',
        function (expr, ind) {
            return ('Unexpected suffix after correct expression at position: ' + ind + '\n' + expr + draw(ind, expr.length - ind));
        }
    );

    return {
        'ProtoException': ProtoException,
        'OddClosingParenthesis': OddClosingParenthesis,
        'MissingClosingParenthesis': MissingClosingParenthesis,
        'MissingOperand': MissingOperand,
        'UnknownIdentifier': UnknownIdentifier,
        'UnknownSymbol': UnknownSymbol,
        'MissingOperation': MissingOperation,
        'OddSuffix': OddSuffix,
        'OddOperand': OddOperand,
        'MissingOperationParenthesis': MissingOperationParenthesis
    }
})();

var expression = (function () {
    require(exceptions);

    function instantiate(constructor, args) {
        var temp = Object.create(constructor.prototype);
        constructor.apply(temp, args);
        return temp;
    }

    function Const(x) {
        this.getValue = function () {
            return x
        }
    }

    Const.prototype.toString = function () {
        return this.getValue().toString()
    }
    Const.prototype.prefix = Const.prototype.toString;
    Const.prototype.evaluate = function () {
        return this.getValue()
    }

    function Variable(type) {
        var ind = types[type];
        this.getName = function () {
            return type
        }
        this.getType = function () {
            return ind
        }
    }

    Variable.prototype.toString = function () {
        return this.getName()
    }
    Variable.prototype.prefix = Variable.prototype.toString;
    Variable.prototype.evaluate = function () {
        return arguments[this.getType()]
    }

    function Operation() {
        var operands = [].slice.call(arguments);
        this.getOperands = function () {
            return operands
        }
    }

    Operation.prototype.toString = function () {
        return this.getOperands().join(' ') + ' ' + this.getSymbol();
    }
    Operation.prototype.prefix = function () {
        return '(' + this.getSymbol() + ' ' + this.getOperands().map(function (value) {
            return value.prefix()
        }).join(' ') + ')';
    }
    Operation.prototype.evaluate = function () {
        var args = arguments;
        var res = this.getOperands().map(function (value) {
            return value.evaluate.apply(value, args)
        });
        return this.delegate.apply(null, res);
    }

    function isGood(a) {
        return (a instanceof Const || a instanceof Variable || a instanceof Operation);
    }

    function DefineOperation(maker, action, symbol) {
        this.constructor = maker;
        this.delegate = action;
        this.getSymbol = function () {
            return symbol;
        }
    }

    DefineOperation.prototype = Operation.prototype;

    function operationFactory(action, symbol) {
        var result = function () {
            var args = arguments;
            Operation.apply(this, args);
        }
        result.prototype = new DefineOperation(result, action, symbol);
        return result;
    }

    var Add = operationFactory(function(a, b) {return a + b}, '+');

    var Subtract = operationFactory(function(a, b) {return a - b}, '-');

    var Multiply = operationFactory(function(a, b) {return a * b}, '*');

    var Divide = operationFactory(function(a, b) {return a / b}, '/');

    var Negate = operationFactory(function(a) {return  -a}, 'negate');

    var ArcTan = operationFactory(function(a) {return  Math.atan(a)}, 'atan');

    var Exp = operationFactory(function(a) {return  Math.exp(a)}, 'exp');

    var Aliases = {
        '+': Add,
        '-': Subtract,
        '*': Multiply,
        '/': Divide,
        'negate': Negate,
        'atan': ArcTan,
        'exp': Exp
    };
    var Arity = {
        '+': 2,
        '-': 2,
        '*': 2,
        '/': 2,
        'negate': 1,
        'atan': 1,
        'exp': 1
    };

    var expression = '';
    var position = 0;
    var stack = [];
    var positions = [];

    function skip() {
        while (position < expression.length && /\s/.test(expression.charAt(position))) {
            position++;
        }
    }

    function parseNumber() {
        var res = '';
        if (expression.charAt(position) === '-') {
            res += '-';
            position++;
        }
        while (position < expression.length && /\d/.test(expression.charAt(position))) {
            res += expression.charAt(position++);
        }
        return res;
    }

    function handleEmptyInput() {
        skip();
        if (position === expression.length) {
            throw new ProtoException('Empty input');
        }
    }

    function getIdentifier() {
        if (!(/[A-Za-z]/.test(expression.charAt(position)))) {
            throw new UnknownSymbol(expression, position);
        }
        var result = '';
        while (position < expression.length && /\w/.test(expression.charAt(position))) {
            result += expression.charAt(position++);
        }
        return result;
    }

    function tryParse() {
        var currentNumber = parseNumber();
        if (currentNumber !== '' && currentNumber !== '-') {
            return parseInt(currentNumber);
        }
        if (currentNumber === '-') {
            position--;
        }
        return undefined;
    }

    function last() {
        return stack[stack.length - 1];
    }

    function execute(mode) {
        var currentIndex = undefined;
        var currentOperation = undefined;
        var operands = [];
        while ((last() !== '(') && !(last() in Aliases)) {
            operands.push(stack.pop());
            positions.pop();
        }
        if (last() === '(') {
            throw new MissingOperation(expression, positions.pop(), mode);
        }
        currentOperation = stack.pop();
        currentIndex = positions.pop();
        if (stack.pop() !== '(') {
            throw new MissingOpeationParenthesis(expression, currentOperation, positions.pop(), mode);
        }
        positions.pop()
        if (operands.length > Arity[currentOperation]) {
            throw new OddOperand(expression, currentOperation, currentIndex);
        } else if (operands.length < Arity[currentOperation]) {
            throw new MissingOperand(expression, currentOperation, currentIndex);
        } else {
            stack.push(instantiate(Aliases[currentOperation], operands.reverse()));
        }
    }

    function parse(s, mode) {
        var balance = 0;
        position = 0;
        expression = s;
        stack = [];
        handleEmptyInput();
        while (true) {
            skip();
            if (position >= expression.length) {
                break;
            }
            if (expression.charAt(position) === ')') {
                balance--;
                if (balance < 0) {
                    throw new OddClosingParenthesis(expression, position);
                }
                execute(mode);
                position++;
                if (balance === 0) {
                    break;
                }
                continue;
            }
            positions.push(position);
            if (expression.charAt(position) === '(') {
                stack.push('(');
                position++;
                balance++;
                continue;
            }
            var number = tryParse();
            if (number !== undefined) {
                stack.push(new Const(number));
                continue;
            }
            var currentOperation = undefined;
            var currentIdentifier;
            if (expression.charAt(position) in Aliases) {
                currentOperation = expression.charAt(position);
                position++;
            } else {
                currentIdentifier = getIdentifier();
                if (currentIdentifier in Aliases) {
                    currentOperation = currentIdentifier;
                }
            }
            if (currentOperation !== undefined) {
                stack.push(currentOperation);
            } else if (currentIdentifier in types) {
                stack.push(new Variable(currentIdentifier));
                if (balance === 0) {
                    break;
                }
            } else {
                throw new UnknownIdentifier(expression, currentIdentifier, positions.pop());
            }
        }
        skip();
        if (position !== expression.length) {
            throw new OddSuffix(expression, position);
        } else if (balance > 0) {
            throw new MissingClosingParenthesis(expression, position);
        } else if (stack.length > 1) {
            throw new MissingOpeationParenthesis(expression, stack[0], positions[0], mode);
        }
        var res = stack.pop();
        if (!isGood(res)) {
            throw new MissingOpeationParenthesis(expression, res, positions.pop(), mode);
        }
        return res;
    }

    function parsePrefix(s) {
        return parse(s, 0);
    }

    return {
        'Const': Const,
        'Variable': Variable,
        'Add': Add,
        'Subtract': Subtract,
        'Multiply': Multiply,
        'Divide': Divide,
        'Negate': Negate,
        'ArcTan': ArcTan,
        'Exp': Exp,
        'parsePrefix': parsePrefix,
    }
})();

require(expression);