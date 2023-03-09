package com.example.quiz;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static org.junit.Assert.assertTrue;

import android.content.Intent;
import android.graphics.Bitmap;

import androidx.test.core.app.ActivityScenario;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.filters.LargeTest;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */

@RunWith(AndroidJUnit4.class)
@LargeTest
public class PokemonAppTest {

    @Rule
    public ActivityScenarioRule<MainActivity> mainActivityRule =
            new ActivityScenarioRule<>(MainActivity.class);

    @Rule
    public ActivityScenarioRule<QuizActivity> quizActivityRule =
            new ActivityScenarioRule<>(QuizActivity.class);

    @Test
    public void testButtonsMain() {
        // Button 1
        // Find the button and click it
        Espresso.onView(ViewMatchers.withId(R.id.answersBtn))
                .perform(ViewActions.click());

        // Verify that the new activity is launched
        Espresso.onView(ViewMatchers.withId(R.id.ansContainer))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));

        // Button 2
        Espresso.onView(ViewMatchers.withId(R.id.addEntryBtn))
                .perform(ViewActions.click());

        Espresso.onView(ViewMatchers.withId(R.id.addContainer))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));

        // Button 3
        Espresso.onView(ViewMatchers.withId(R.id.quizBtn))
                .perform(ViewActions.click());

        Espresso.onView(ViewMatchers.withId(R.id.quizContainer))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
    }

    @Test
    public void testDisplayNextQuestion() {
        onView(withId(R.id.btn1)).perform(ViewActions.click()); // Click on the correct answer button
        onView(withId(R.id.scoreBoard)).check(ViewAssertions.matches(ViewMatchers.withText("Score: 1/1"))); // Check that score is updated
        onView(withId(R.id.feedback)).check(ViewAssertions.matches(ViewMatchers.withText(""))); // Check that feedback text is empty
        onView(withId(R.id.simpleProgressBar)).check(matches(withProgress(1))); // Check that progress bar is updated
        // Click on the exit button to return to the main menu
        onView(withId(R.id.exit)).perform(ViewActions.click());
    }
    @Test
    public void testTimer() {
        // Set the choice flag to true
        Intent intent = new Intent(ApplicationProvider.getApplicationContext(), QuizActivity.class);
        intent.putExtra("choice", true);
        ActivityScenario<QuizActivity> activityScenario = ActivityScenario.launch(intent);
        activityScenario.onActivity(activity -> {
            // Check that the timer is visible and set to 10 seconds
            onView(withId(R.id.simpleProgressBar)).check(matches(isDisplayed()));
            // Wait for 5 seconds
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // Check that the timer has progressed by 5 seconds
            onView(withId(R.id.simpleProgressBar)).check(matches(isDisplayed()));
            // Click on the correct answer button
            onView(withId(R.id.btn1)).perform(click());
            // Check that the timer has stopped and the score has been updated
            onView(withId(R.id.simpleProgressBar)).check(matches(isDisplayed()));
            onView(withId(R.id.scoreBoard)).check(matches(withText("Score: 1/1")));
            // Click on the exit button to return to the main menu
            onView(withId(R.id.exit)).perform(click());
        });
    }
}