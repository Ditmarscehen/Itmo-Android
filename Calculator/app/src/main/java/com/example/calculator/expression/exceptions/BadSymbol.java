package com.example.calculator.expression.exceptions;

public class BadSymbol extends ExpressionException {
    public BadSymbol(String message, int pos) {
        super(message + "\n" );
    }
}
