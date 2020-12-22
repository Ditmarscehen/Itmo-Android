package com.example.calculator

import com.example.calculator.expression.exceptions.DivideByZero
import com.example.calculator.expression.generic.GenericTabulator.evaluateInt
import org.junit.Assert.assertEquals
import org.junit.Test


class EvaluateUnitTest {

    @Test
    fun addition_isCorrect() {
        assertEquals(4, evaluateInt("2 + 2"))
    }

    @Test
    fun subtract_isCorrect() {
        assertEquals(0, evaluateInt("2 - 2"))
    }

    @Test
    fun multiply_isCorrect() {
        assertEquals(4, evaluateInt("2 * 2"))
    }

    @Test
    fun divide_isCorrect() {
        assertEquals(1, evaluateInt("2 / 2"))
    }

    @Test
    fun all_isCorrect() {
        assertEquals(5, evaluateInt("2 + 2 * 2 -2 / 2"))
    }

    @Test(expected = DivideByZero::class)
    fun divisionByZero_isCorrect() {
        evaluateInt("2 / 0")
    }

}