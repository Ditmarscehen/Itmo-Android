package com.example.calculator


import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.calculator.TestsCompanion.Companion.click
import com.example.calculator.TestsCompanion.Companion.match
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class IllegalAnswerTest {
    private lateinit var expectedValue:String
    @get:Rule
    var activityRule: ActivityScenarioRule<MainActivity> =
        ActivityScenarioRule(MainActivity::class.java)
    @Before
    fun initialValidResult(){
        expectedValue = "1/0"
    }
    @Test
    fun checkIllegalAnswerEvaluate() {
        click(R.id.button1)
        click(R.id.buttonDivide)
        click(R.id.button0)
        click(R.id.buttonEvaluate)
        match(R.id.input,expectedValue)
    }
}