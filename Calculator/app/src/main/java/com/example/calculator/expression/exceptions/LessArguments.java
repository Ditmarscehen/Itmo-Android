package com.example.calculator.expression.exceptions;

public class LessArguments extends ExpressionException {
    public LessArguments(String message, int pos) {
        super(message + "\n" );
    }
}
