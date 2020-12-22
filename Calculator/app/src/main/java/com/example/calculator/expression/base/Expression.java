package com.example.calculator.expression.base;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */

public interface Expression extends ToMiniString {
    int evaluate(int x);
}