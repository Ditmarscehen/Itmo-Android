package com.example.calculator.expression.base;

import com.example.calculator.expression.exceptions.ExpressionException;
import com.example.calculator.expression.generic.TripleExpression;
import com.example.calculator.expression.generic.operations.Operation;

public class Multiply<T> extends AbstractFunction<T> {

    public Multiply(com.example.calculator.expression.generic.TripleExpression<T> first, TripleExpression<T> second, Operation<T> type) {
        super(first, second, type);
    }

    protected T calculate(T x, T y) throws ExpressionException {
        return type.mul(x, y);
    }
}
