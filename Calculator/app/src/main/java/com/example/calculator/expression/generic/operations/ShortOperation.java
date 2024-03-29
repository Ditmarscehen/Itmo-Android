package com.example.calculator.expression.generic.operations;

import com.example.calculator.expression.exceptions.ExpressionException;
import com.example.calculator.expression.exceptions.NumberFormat;

public class ShortOperation implements Operation<Short> {

    public Short parseNum(String num) throws ExpressionException {
        try {
            return Short.parseShort(num);
        } catch (NumberFormatException e) {
            throw new NumberFormat("Wrong number format", 0);
        }
    }

    @Override
    public Short add(Short x, Short y) throws ExpressionException {
        return (short) (x + y);
    }

    @Override
    public Short sub(Short x, Short y) throws ExpressionException {
        return (short) (x - y);
    }

    @Override
    public Short mul(Short x, Short y) throws ExpressionException {
        return (short) (x * y);
    }

    @Override
    public Short div(Short x, Short y) throws ExpressionException {
        return (short) (x / y);
    }

    @Override
    public Short not(Short x) throws ExpressionException {
        return (short) -x;
    }

    @Override
    public Short min(Short x, Short y) throws ExpressionException {
        return (x > y) ? y : x;
    }

    @Override
    public Short max(Short x, Short y) throws ExpressionException {
        return (x > y) ? x : y;
    }
}
