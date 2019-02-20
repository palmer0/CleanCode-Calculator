package es.ulpgc.eite.cleancode.calculator;


import android.support.test.espresso.ViewInteraction;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import es.ulpgc.eite.cleancode.calculator.R;
import es.ulpgc.eite.cleancode.calculator.basic.BasicActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class BasicActivityInstrumentedTests {

  @Rule
  public ActivityTestRule<BasicActivity> mActivityTestRule
      = new ActivityTestRule<>(BasicActivity.class);

  @Test
  public void basicActivityTests() {
    ViewInteraction textView = onView(
        allOf(withId(R.id.textDisplay), withText("0"),
            isDisplayed()));
    textView.check(matches(withText("0")));

    ViewInteraction appCompatButton = onView(
        allOf(withId(R.id.button2), withText("2"),
            isDisplayed()));
    appCompatButton.perform(click());

    ViewInteraction textView2 = onView(
        allOf(withId(R.id.textDisplay), withText("2"),
            isDisplayed()));
    textView2.check(matches(withText("2")));

    ViewInteraction appCompatButton2 = onView(
        allOf(withId(R.id.button3), withText("3"),
            isDisplayed()));
    appCompatButton2.perform(click());

    ViewInteraction textView3 = onView(
        allOf(withId(R.id.textDisplay), withText("23"),
            isDisplayed()));
    textView3.check(matches(withText("23")));

    ViewInteraction appCompatButton3 = onView(
        allOf(withId(R.id.buttonPlus), withText("+"),
            isDisplayed()));
    appCompatButton3.perform(click());

    ViewInteraction textView4 = onView(
        allOf(withId(R.id.textDisplay), withText("23"),
            isDisplayed()));
    textView4.check(matches(withText("23")));

    /*
    try {
      Thread.sleep(700);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    */

    ViewInteraction textView5 = onView(
        allOf(withId(R.id.textDisplay), withText("23"),
            isDisplayed()));
    textView5.check(matches(withText("23")));

    /*
    ViewInteraction button = onView(
        allOf(withId(R.id.buttonMult),
            isDisplayed()));
    button.check(matches(isDisplayed()));

    ViewInteraction button2 = onView(
        allOf(withId(R.id.buttonDiv),
            isDisplayed()));
    button2.check(matches(isDisplayed()));
    */

    ViewInteraction appCompatButton4 = onView(
        allOf(withId(R.id.button7), withText("7"),
            isDisplayed()));
    appCompatButton4.perform(click());

    ViewInteraction textView6 = onView(
        allOf(withId(R.id.textDisplay), withText("7"),
            isDisplayed()));
    textView6.check(matches(withText("7")));

    ViewInteraction appCompatButton5 = onView(
        allOf(withId(R.id.buttonEqual), withText("="),
            isDisplayed()));
    appCompatButton5.perform(click());

    ViewInteraction textView7 = onView(
        allOf(withId(R.id.textDisplay), withText("30"),
            isDisplayed()));
    textView7.check(matches(withText("30")));

    ViewInteraction appCompatButton6 = onView(
        allOf(withId(R.id.buttonMinus), withText("-"),
            isDisplayed()));
    appCompatButton6.perform(click());

    ViewInteraction textView8 = onView(
        allOf(withId(R.id.textDisplay), withText("30"),
            isDisplayed()));
    textView8.check(matches(withText("30")));

    /*
    try {
      Thread.sleep(700);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    */

    ViewInteraction textView9 = onView(
        allOf(withId(R.id.textDisplay), withText("30"),
            isDisplayed()));
    textView9.check(matches(withText("30")));

    ViewInteraction appCompatButton7 = onView(
        allOf(withId(R.id.button5), withText("5"),
            isDisplayed()));
    appCompatButton7.perform(click());

    ViewInteraction textView10 = onView(
        allOf(withId(R.id.textDisplay), withText("5"),
            isDisplayed()));
    textView10.check(matches(withText("5")));

    ViewInteraction appCompatButton8 = onView(
        allOf(withId(R.id.buttonEqual), withText("="),
            isDisplayed()));
    appCompatButton8.perform(click());

    ViewInteraction textView11 = onView(
        allOf(withId(R.id.textDisplay), withText("25"),
            isDisplayed()));
    textView11.check(matches(withText("25")));
  }


}
