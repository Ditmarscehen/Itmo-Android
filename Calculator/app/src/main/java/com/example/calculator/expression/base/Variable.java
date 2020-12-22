package com.example.calculator.expression.base;

import com.example.calculator.expression.exceptions.ExpressionException;
import com.example.calculator.expression.generic.TripleExpression;

public class Variable<T> implements TripleExpression<T> {
    private String var;

    public Variable(String var) {
        this.var = var;
    }


    @Override
    public T evaluate(T x, T y, T z) {
        switch (var) {
            case("x"):
                return x;
            case("y"):
                return y;
            case("z"):
                return z;
        }
        throw new ExpressionException("Wrong variable");
    }

    @Override
    public String toMiniString() {
        return toString();
    }
}
