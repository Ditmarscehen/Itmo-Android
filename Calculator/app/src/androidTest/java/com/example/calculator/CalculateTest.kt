package com.example.calculator

import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.example.calculator.TestsCompanion.Companion.click
import com.example.calculator.TestsCompanion.Companion.match
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
@LargeTest
class CalculateTest {
    private lateinit var expectedValue: String

    @get:Rule
    var activityRule: ActivityScenarioRule<MainActivity> =
        ActivityScenarioRule(MainActivity::class.java)

    @Before
    fun initialValidResult() {
        expectedValue = "60"
        //10 * 6 - 8 / 2 + 4
    }

    @Test
    fun checkCalculateScenario() {
        click(R.id.button1)
        click(R.id.button0)
        click(R.id.buttonMultiply)
        click(R.id.button6)
        click(R.id.buttonSubtract)
        click(R.id.button8)
        click(R.id.buttonDivide)
        click(R.id.button3)
        click(R.id.buttonDelete)
        click(R.id.button2)
        click(R.id.buttonAdd)
        click(R.id.button4)
        click(R.id.buttonEvaluate)
        match(R.id.input, expectedValue)

    }
}