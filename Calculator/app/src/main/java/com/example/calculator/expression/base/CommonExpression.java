package com.example.calculator.expression.base;

import com.example.calculator.expression.generic.TripleExpression;

public interface CommonExpression extends Expression, DoubleExpression, TripleExpression {
    String toString();
}
