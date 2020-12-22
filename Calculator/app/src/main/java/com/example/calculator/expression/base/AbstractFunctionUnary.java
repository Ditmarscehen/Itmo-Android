package com.example.calculator.expression.base;

import com.example.calculator.expression.exceptions.ExpressionException;
import com.example.calculator.expression.generic.TripleExpression;
import com.example.calculator.expression.generic.operations.Operation;

abstract class AbstractFunctionUnary<T> implements com.example.calculator.expression.generic.TripleExpression<T> {
    private final com.example.calculator.expression.generic.TripleExpression<T> first;
    protected Operation<T> type;

    AbstractFunctionUnary(TripleExpression<T> first, Operation <T> type) {
        this.first = first;
        this.type = type;
    }
    @Override
    public String toMiniString(){
        return toString();
    }
    @Override
    public T evaluate(T x, T y, T z) throws ExpressionException {
        return calculate(first.evaluate(x, y, z));
    }

    protected abstract T calculate(T x) throws ExpressionException;
}
