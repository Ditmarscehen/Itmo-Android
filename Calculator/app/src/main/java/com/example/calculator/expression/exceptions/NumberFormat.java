package com.example.calculator.expression.exceptions;

public class NumberFormat extends ExpressionException {
    public NumberFormat(String message, int pos) {
        super(message + "\n" );
    }
}
