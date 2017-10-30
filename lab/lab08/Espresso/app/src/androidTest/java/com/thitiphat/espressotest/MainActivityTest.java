package com.thitiphat.espressotest;


import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.assertion.ViewAssertions.matches;

import android.os.SystemClock;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.view.View;

import org.hamcrest.Matcher;

import static android.support.test.espresso.matcher.ViewMatchers.hasDescendant;
import static org.hamcrest.Matchers.not;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.RootMatchers.withDecorView;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void mainActivityTest() {

    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {
        return parentMatcher;
    }

    public static RecyclerViewMatcher withRecyclerView(final int recyclerViewId) {
        return new RecyclerViewMatcher(recyclerViewId);
    }

    //Insert name only
    @Test
    public void testCase1() {
        SystemClock.sleep(500);
        onView(withId(R.id.etName)).perform(typeText("Ying"), closeSoftKeyboard());
        SystemClock.sleep(1000);
        onView(withId(R.id.btnAdd)).perform(click());
        SystemClock.sleep(1000);
        onView(withText("Please Enter user info"))
                .inRoot(withDecorView(not(mActivityTestRule.getActivity().getWindow().getDecorView())))
                .check(matches(isDisplayed()));
        SystemClock.sleep(1500);
    }

    //Insert age only
    @Test
    public void testCase2() {
        SystemClock.sleep(500);
        onView(withId(R.id.etAge)).perform(typeText("20"), closeSoftKeyboard());
        SystemClock.sleep(1000);
        onView(withId(R.id.btnAdd)).perform(click());
        SystemClock.sleep(1000);
        onView(withText("Please Enter user info"))
                .inRoot(withDecorView(not(mActivityTestRule.getActivity().getWindow().getDecorView())))
                .check(matches(isDisplayed()));
        SystemClock.sleep(1500);
    }

    //is list empty?
    @Test
    public void testCase3() {
        SystemClock.sleep(500);
        onView(withId(R.id.btnView)).perform(click());
        SystemClock.sleep(1000);
        onView(withText("Not Found"))
                .inRoot(withDecorView(not(mActivityTestRule.getActivity().getWindow().getDecorView())))
                .check(matches(isDisplayed()));
        SystemClock.sleep(1500);
    }

    //Input Nothing
    @Test
    public void testCase4() {
        SystemClock.sleep(500);
        onView(withId(R.id.btnAdd)).perform(click());
        SystemClock.sleep(1000);
        onView(withText("Please Enter user info"))
                .inRoot(withDecorView(not(mActivityTestRule.getActivity().getWindow().getDecorView())))
                .check(matches(isDisplayed()));
        SystemClock.sleep(1500);
    }

    //Case - Ying 20
    @Test
    public void testCase5() {
        onView(withId(R.id.etName)).perform(typeText("Ying"), closeSoftKeyboard());
        SystemClock.sleep(1000);
        onView(withId(R.id.etAge)).perform(typeText("20"), closeSoftKeyboard());
        SystemClock.sleep(1000);
        onView(withId(R.id.btnAdd)).perform(click());
        SystemClock.sleep(1000);
        onView(withId(R.id.btnView)).perform(click());
        SystemClock.sleep(1000);
        onView(withRecyclerView(R.id.itemList).atPositionOnView(0, R.id.tvName))
                .check(matches(withText("Ying")));
        onView(withRecyclerView(R.id.itemList).atPositionOnView(0, R.id.tvAge))
                .check(matches(withText("20")));
        SystemClock.sleep(2000);
    }

    //Case - Ladarat 20
    @Test
    public void testCase6() {
        onView(withId(R.id.etName)).perform(typeText("Ladarat"), closeSoftKeyboard());
        SystemClock.sleep(1000);
        onView(withId(R.id.etAge)).perform(typeText("20"), closeSoftKeyboard());
        SystemClock.sleep(1000);
        onView(withId(R.id.btnAdd)).perform(click());
        SystemClock.sleep(1000);
        onView(withId(R.id.btnView)).perform(click());
        SystemClock.sleep(1000);
        onView(withRecyclerView(R.id.itemList).atPositionOnView(1, R.id.tvName))
                .check(matches(withText("Ladarat")));
        onView(withRecyclerView(R.id.itemList).atPositionOnView(1, R.id.tvAge))
                .check(matches(withText("20")));
        SystemClock.sleep(2000);
    }

    //Case - Somkiat 80
    @Test
    public void testCase7() {
        onView(withId(R.id.etName)).perform(typeText("Somkait"), closeSoftKeyboard());
        SystemClock.sleep(1000);
        onView(withId(R.id.etAge)).perform(typeText("80"), closeSoftKeyboard());
        SystemClock.sleep(1000);
        onView(withId(R.id.btnAdd)).perform(click());
        SystemClock.sleep(1000);
        onView(withId(R.id.btnView)).perform(click());
        SystemClock.sleep(1000);
        onView(withRecyclerView(R.id.itemList).atPositionOnView(2, R.id.tvName))
                .check(matches(withText("Somkait")));
        onView(withRecyclerView(R.id.itemList).atPositionOnView(2, R.id.tvAge))
                .check(matches(withText("80")));
        SystemClock.sleep(2000);
    }

    //Case - Prayoch 60
    @Test
    public void testCase8() {
        onView(withId(R.id.etName)).perform(typeText("Prayoch"), closeSoftKeyboard());
        SystemClock.sleep(1000);
        onView(withId(R.id.etAge)).perform(typeText("60"), closeSoftKeyboard());
        SystemClock.sleep(1000);
        onView(withId(R.id.btnAdd)).perform(click());
        SystemClock.sleep(1000);
        onView(withId(R.id.btnView)).perform(click());
        SystemClock.sleep(1000);
        onView(withRecyclerView(R.id.itemList).atPositionOnView(3, R.id.tvName))
                .check(matches(withText("Prayoch")));
        onView(withRecyclerView(R.id.itemList).atPositionOnView(3, R.id.tvAge))
                .check(matches(withText("60")));
        SystemClock.sleep(2000);
    }

    //Case - Prayoch 50
    @Test
    public void testCase9() {
        onView(withId(R.id.etName)).perform(typeText("Prayoch"), closeSoftKeyboard());
        SystemClock.sleep(1000);
        onView(withId(R.id.etAge)).perform(typeText("50"), closeSoftKeyboard());
        SystemClock.sleep(1000);
        onView(withId(R.id.btnAdd)).perform(click());
        SystemClock.sleep(1000);
        onView(withId(R.id.btnView)).perform(click());
        SystemClock.sleep(1000);
        onView(withRecyclerView(R.id.itemList).atPositionOnView(4, R.id.tvName))
                .check(matches(withText("Prayoch")));
        onView(withRecyclerView(R.id.itemList).atPositionOnView(4, R.id.tvAge))
                .check(matches(withText("50")));
        SystemClock.sleep(2000);
    }
}
