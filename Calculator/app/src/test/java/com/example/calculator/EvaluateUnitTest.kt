package com.example.calculator

import com.example.calculator.expression.exceptions.DivideByZero
import com.example.calculator.expression.generic.operations.IntOperation
import com.example.calculator.expression.parser.ExpressionParser
import org.junit.Assert.assertEquals
import org.junit.Test


class EvaluateUnitTest {

    @Test
    fun addition_isCorrect() {
        assertEquals(4, ExpressionParser(IntOperation()).parse("2 + 2").evaluate(0, 0, 0))
    }

    @Test
    fun subtract_isCorrect() {
        assertEquals(0, ExpressionParser(IntOperation()).parse("2 - 2").evaluate(0, 0, 0))
    }

    @Test
    fun multiply_isCorrect() {
        assertEquals(4, ExpressionParser(IntOperation()).parse("2 * 2").evaluate(0, 0, 0))
    }

    @Test
    fun divide_isCorrect() {
        assertEquals(1, ExpressionParser(IntOperation()).parse("2 / 2").evaluate(0, 0, 0))
    }

    @Test
    fun all_isCorrect() {
        assertEquals(5, ExpressionParser(IntOperation()).parse("2 * 2 + 2 / 2").evaluate(0, 0, 0))
    }

    @Test(expected = DivideByZero::class)
    fun nullStringTest() {
        ExpressionParser(IntOperation()).parse("2 / 0").evaluate(0, 0, 0)
    }

}