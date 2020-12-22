package com.example.calculator.expression.generic;


import com.example.calculator.expression.base.ToMiniString;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public interface TripleExpression<T> extends ToMiniString {
    T evaluate(T x, T y, T z);
}