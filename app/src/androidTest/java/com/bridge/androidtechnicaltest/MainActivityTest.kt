package com.bridge.androidtechnicaltest

import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.bridge.androidtechnicaltest.ui.MainActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @get:Rule
    var activityTestRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun launch_app_test() {
        delay()

        Espresso.onView(ViewMatchers.withId(R.id.container))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        delay()
    }

    private fun delay() {
        try {
            Thread.sleep(3000)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
    }

}