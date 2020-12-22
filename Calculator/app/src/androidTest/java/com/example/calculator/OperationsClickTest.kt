import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.calculator.MainActivity
import com.example.calculator.R
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class OperationsClickTest {

    @get:Rule
    var activityRule: ActivityScenarioRule<MainActivity> =
        ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun checkConsecutiveOperations() {
        Espresso.onView(ViewMatchers.withId(R.id.button1)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.buttonAdd)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.buttonSubtract)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.button2)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.input))
            .check(ViewAssertions.matches(ViewMatchers.withText("1+2")))

    }

    @Test
    fun beginWithOperation() {
        Espresso.onView(ViewMatchers.withId(R.id.buttonAdd)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.buttonMultiply)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.buttonDivide)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.input))
            .check(ViewAssertions.matches(ViewMatchers.withText("")))
    }

}