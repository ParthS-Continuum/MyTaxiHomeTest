package com.mytaxi.android_demo;

import android.support.test.espresso.ViewAssertion;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import com.mytaxi.android_demo.activities.MainActivity;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.clearText;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.RootMatchers.withDecorView;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsNot.not;

@RunWith(AndroidJUnit4.class)
@LargeTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class HomePage extends LoginMyTaxi {

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule <>(MainActivity.class);

    private MainActivity mActivity = null;

    @Before
    public void setActivity() {
        mActivity = mActivityRule.getActivity();
    }

    public void selectDriver(String searchKeyword, String driverName) throws InterruptedException {

        onView(withId(R.id.textSearch))
                .check(matches(isDisplayed()));//Validate SearchBar field
        onView(withId(R.id.textSearch))
                .perform(clearText());
        onView(withId(R.id.textSearch))
                .perform(typeText(searchKeyword), closeSoftKeyboard());//Enter searchKeyword

        Thread.sleep(2000);
        // Checking suggestions which are displayed.
        onView(withText("Sara Christensen"))
                .inRoot(withDecorView(not(is(mActivity.getWindow().getDecorView()))))
                .check(matches(isDisplayed()));

        onView(withText("Sarah Scott"))
                .inRoot(withDecorView(not(is(mActivity.getWindow().getDecorView()))))
                .check(matches(isDisplayed()));

        Thread.sleep(2000);
        // Tap on a 2nd Driver suggestion.
        onView(withText(driverName))
                .inRoot(withDecorView(not(is(mActivity.getWindow().getDecorView()))))
                .perform(click());

        Log.d("Select Driver","Driver has been selected.");
    }
        public void validateDriver() {
            // Checking the Drivers Profile.
            onView(withText("Driver Profile")).check(matches(isDisplayed()));

            onView(withId(R.id.textViewDriverName))
                    .check(matches(withText("Sarah Scott")));

            Log.d("Validate Driver","Driver has been verified.");
        }

    public void callDriver() throws InterruptedException {

        onView(withId(R.id.fab))
                .check(matches(isDisplayed()));

        Thread.sleep(2000);
        onView(withId(R.id.fab))
                .perform(click());

        Log.d("Call Driver","Driver has been called.");
    }

    @Test
    public void bookDriver() throws InterruptedException {
        performLogin();
        Thread.sleep(2000);
        selectDriver("sa","Sarah Scott");
        Thread.sleep(2000);
        validateDriver();
        Thread.sleep(2000);
        callDriver();
    }
}
