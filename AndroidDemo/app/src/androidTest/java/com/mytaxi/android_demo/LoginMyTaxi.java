package com.mytaxi.android_demo;

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
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.StringStartsWith.startsWith;

@RunWith(AndroidJUnit4.class)
@LargeTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class LoginMyTaxi {

    public String uname="crazydog335";
    public String pwd="venture";


    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule <>(MainActivity.class);


    @Before
    public void setUp() throws Exception{
    }


    public void login(String username, String password) throws InterruptedException {

        onView(withId(R.id.edt_username))
                .check(matches(isDisplayed()));//Validate Username field
        onView(withId(R.id.edt_username))
                .perform(clearText());
        onView(withId(R.id.edt_username))
                .perform(typeText(username));//Enter Username

        onView(withId(R.id.edt_password))
                .check(matches(isDisplayed()));//Validate Username field
        onView(withId(R.id.edt_password))
                .perform(clearText());
        onView(withId(R.id.edt_password))
                .perform(typeText(password));//Enter Password

        Thread.sleep(2000);
        onView(withId(R.id.btn_login))
                .check(matches(isDisplayed()));//Validate Login button
        onView(withId(R.id.btn_login))
                .perform(click());

    }

    public void validateLoginUser(String loginUser) throws InterruptedException {

        onView(withText("mytaxi demo"))
                .check(matches(isDisplayed()));//Validate Username field
        onView(withContentDescription("Open navigation drawer"))
                .perform(click());

        Thread.sleep(2000);
        onView(withId(R.id.nav_username))
                .check(matches(isDisplayed()));
        onView(withId(R.id.nav_username))
                .check(matches(withText("crazydog335")));//Validate Username

        Thread.sleep(2000);
        onView(withId(R.id.fab))
                .perform(click());
        Log.d("LoginSuccess","Login successful");
    }

    @Test
    public void performLogin() throws InterruptedException {

        login(uname,pwd);
        Thread.sleep(4000);
        validateLoginUser(uname);
    }

    public void performLogout() throws InterruptedException {
        Thread.sleep(4000);
        onView(withContentDescription("Open navigation drawer"))
                .perform(click());
        Thread.sleep(2000);

        onView(withId(R.id.design_menu_item_text))
                .check(matches(isDisplayed()));//Validate Logout button
        onView(withId(R.id.design_menu_item_text))
                .perform(click());//Logging Out
        Log.d("LogoutSuccess","Logout successful");
    }
    @After
    public void tearDown() throws Exception{
        //performLogout();
    }

}
