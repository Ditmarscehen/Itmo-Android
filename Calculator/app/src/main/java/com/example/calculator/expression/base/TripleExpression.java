package com.example.calculator.expression.base;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public interface TripleExpression extends ToMiniString {
    int evaluate(int x, int y, int z);
}