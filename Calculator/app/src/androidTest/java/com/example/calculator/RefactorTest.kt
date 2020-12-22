package com.example.calculator


import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class RefactorTest {
    @get:Rule
    var activityRule: ActivityScenarioRule<MainActivity> =
        ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun checkRecreate() {
        onView(withId(R.id.button1)).perform(click())
        onView(withId(R.id.buttonAdd)).perform(click())
        onView(withId(R.id.button2)).perform(click())
        onView(withId(R.id.input)).check(matches(withText("1+2")))
        activityRule.scenario.recreate()
        onView(withId(R.id.input)).check(matches(withText("1+2")))
    }

}


