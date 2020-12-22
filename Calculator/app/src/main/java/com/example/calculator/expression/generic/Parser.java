package com.example.calculator.expression.generic;

import com.example.calculator.expression.exceptions.ExpressionException;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public interface Parser<T> {
    TripleExpression<T> parse(String expression) throws ExpressionException;
}