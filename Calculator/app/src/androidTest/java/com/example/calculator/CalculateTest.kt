package com.example.calculator

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
@LargeTest
class CalculateTest {
    @get:Rule
    var activityRule: ActivityScenarioRule<MainActivity> =
        ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun checkCalculateScenario() {
        Espresso.onView(ViewMatchers.withId(R.id.button1)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.buttonAdd)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.button2)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.buttonMultiply)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.button3)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.output))
            .check(ViewAssertions.matches(ViewMatchers.withText("7")))
        Espresso.onView(ViewMatchers.withId(R.id.buttonEvaluate)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.input))
            .check(ViewAssertions.matches(ViewMatchers.withText("7")))
        Espresso.onView(ViewMatchers.withId(R.id.output))
            .check(ViewAssertions.matches(ViewMatchers.withText("")))
    }
}