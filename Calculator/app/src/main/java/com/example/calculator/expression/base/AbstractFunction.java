package com.example.calculator.expression.base;

import com.example.calculator.expression.exceptions.ExpressionException;
import com.example.calculator.expression.generic.TripleExpression;
import com.example.calculator.expression.generic.operations.Operation;

import java.util.Objects;

abstract class AbstractFunction<T> implements com.example.calculator.expression.generic.TripleExpression<T> {
    private final com.example.calculator.expression.generic.TripleExpression<T> first;
    private final com.example.calculator.expression.generic.TripleExpression<T> second;
    protected Operation<T> type;

    AbstractFunction(com.example.calculator.expression.generic.TripleExpression<T> first, TripleExpression<T> second, Operation <T> type) {
        this.first = first;
        this.second = second;
        this.type = type;
    }

    public String toString() {
        return "(" + first.toString() + " " +
                "op" +
                " " + second.toString() + ")";
    }
    @Override
    public String toMiniString(){
        return toString();
    }
    @Override
    public boolean equals(Object object) {
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        AbstractFunction comp = (AbstractFunction) object;
        return object == this || first.equals(comp.first) && second.equals(comp.second);
    }

    public int hashCode() {
        return 4241 * first.hashCode() + 53 * (Objects.hash("op")) + second.hashCode();
    }


    @Override
    public T evaluate(T x, T y, T z) throws ExpressionException {
        T left = first.evaluate(x, y, z);
        T right = second.evaluate(x, y, z);
        return calculate(left, right);
    }


    protected abstract T calculate(T x, T y) throws ExpressionException;
}
