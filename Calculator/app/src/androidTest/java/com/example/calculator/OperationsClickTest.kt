package com.example.calculator

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.calculator.TestsCompanion.Companion.click
import com.example.calculator.TestsCompanion.Companion.match
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class OperationsClickTest {
    lateinit var expectedValue: String

    @get:Rule
    var activityRule: ActivityScenarioRule<MainActivity> =
        ActivityScenarioRule(MainActivity::class.java)

    @Before
    fun initialValidResult() {
        expectedValue = ""
    }

    @Test
    fun checkBeginWithOperation() {
        click(R.id.buttonAdd)
        click(R.id.buttonDivide)
        click(R.id.buttonMultiply)
        match(R.id.input, "")
    }

}