package com.example.calculator.expression.generic.operations;

import com.example.calculator.expression.exceptions.*;


public class IntOperation implements Operation<Integer> {

    public Integer parseNum(String num) throws ExpressionException {
        try {
            return Integer.parseInt(num);
        } catch (NumberFormatException e) {
            throw new NumberFormat("Wrong number format", 0);
        }
    }

    @Override
    public Integer add(Integer x, Integer y) throws ExpressionException {
        checkedAdd(x, y);
        return x + y;
    }

    private void checkedAdd(Integer x, Integer y) throws ExpressionException {
        if ((x > 0 && Integer.MAX_VALUE - x < y)
                || (x < 0 && Integer.MIN_VALUE - x > y)) {
            throw new Overflow(x + " + " + y);
        }
    }

    @Override
    public Integer sub(Integer x, Integer y) throws ExpressionException {
        checkedSub(x, y);
        return x - y;
    }

    private void checkedSub(Integer x, Integer y) throws ExpressionException {
        if ((y < 0 && y + Integer.MAX_VALUE < x) ||
                (y > 0 && y + Integer.MIN_VALUE > x)) {
            throw new Overflow(x + " - " + y);
        }
    }

    @Override
    public Integer mul(Integer x, Integer y) throws ExpressionException {
        checkedMul(x, y);
        return x * y;
    }

    private void checkedMul(Integer a, Integer b) throws ExpressionException {
        if (a != 0 && b != 0) {
            if (a * b == Integer.MIN_VALUE) {
                return;
            } else if (a == Integer.MIN_VALUE && b == -1 || a == -1 && b == Integer.MIN_VALUE) {
                throw new Overflow(a + "*" + b);
            } else if (Integer.MAX_VALUE / abs(a) < abs(b)) {
                throw new Overflow(a + "*" + b);
            }
        }
    }

    @Override
    public Integer div(Integer x, Integer y) throws ExpressionException {
        checkedDiv(x, y);
        return x / y;
    }

    private void checkedDiv(Integer x, Integer y) throws ExpressionException {
        if (y == 0) {
            throw new DivideByZero(x + " / " + y);
        }
        if (x == Integer.MIN_VALUE && y == -1) {
            throw new Overflow(x + " / " + y);
        }
    }

    @Override
    public Integer not(Integer x) throws ExpressionException {
        checkedNot(x);
        return -x;
    }

    private void checkedNot(Integer x) throws ExpressionException {
        if (x == Integer.MIN_VALUE) {
            throw new Overflow("-" + x);
        }
    }


    public Integer pow2(Integer x) throws ExpressionException {
        checkedPow(x);
        return 1 << x;
    }


    private void checkedPow(Integer x) throws ExpressionException {
        if (x < 0) {
            throw new IllegalExpression("pow2 x", 4);
        }
        if (x > 30) {
            throw new Overflow(2 + "**" + x);
        }
    }

    public Integer log2(Integer x) throws ExpressionException {
        checkedLog(x);
        int res = 0;
        while (x > 1) {
            x /= 2;
            res++;
        }
        return res;
    }

    private void checkedLog(Integer x) throws ExpressionException {
        if (x <= 0) {
            throw new IllegalExpression("log2 x", 4);
        }
    }

    private Integer abs(Integer x) {
        return x >= 0 ? x : -x;
    }


    @Override
    public Integer min(Integer x, Integer y) throws ExpressionException {
        return (x > y) ? y : x;
    }

    @Override
    public Integer max(Integer x, Integer y) throws ExpressionException {
        return (x > y) ? x : y;
    }

    public Integer count(Integer x) throws ExpressionException {
        int c = 0;
        while (x > 0) {
            if (x % 2 != 0)
                c++;
            x /= 2;
        }
        return c;
    }
}
