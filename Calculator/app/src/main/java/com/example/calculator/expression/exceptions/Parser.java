package com.example.calculator.expression.exceptions;

import com.example.calculator.expression.base.TripleExpression;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public interface Parser {
    TripleExpression parse(String expression) throws /* Change me */ ExpressionException;
}