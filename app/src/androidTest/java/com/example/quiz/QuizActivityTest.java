package com.example.quiz;

import static androidx.test.core.app.ApplicationProvider.getApplicationContext;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withSubstring;

import android.content.Context;
import android.content.Intent;
import android.widget.Button;

import androidx.room.Room;
import androidx.test.core.app.ActivityScenario;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.filters.LargeTest;
import androidx.test.runner.AndroidJUnit4;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import androidx.test.ext.junit.rules.ActivityScenarioRule;


/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */

@RunWith(AndroidJUnit4.class)
public class QuizActivityTest {

    private Context context;

    @Before
    public void setUp() {
        // Launch MainActivity and perform necessary initializations
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        ActivityScenario<MainActivity> activityScenario = ActivityScenario.launch(intent);

        activityScenario.onActivity(activity -> {
            context = activity.getApplicationContext();
        });

        activityScenario.close();
    }

    @Test
    public void testRandomButton() {
        // Launch QuizActivity
        Intent intent = new Intent(context, QuizActivity.class);
        ActivityScenario<QuizActivity> activityScenario = ActivityScenario.launch(intent);

        onView(withId(R.id.btn1)).perform(click());

        // If we hit the correct answer (1/3 chance)
        if (ViewMatchers.withId(R.id.scoreBoard).matches(matches(ViewMatchers.withText("Score: 1/1")))) {
            onView(withId(R.id.feedback)).check(matches(withSubstring("")));

        // If we hit the wrong answer (2/3 chance)
        } else if (ViewMatchers.withId(R.id.scoreBoard).matches(matches(ViewMatchers.withText("Score: 0/1")))) {
            onView(withId(R.id.feedback)).check(matches(withSubstring("Right answer was: ")));
        }

        activityScenario.close();
    }
    @Test
    public void testDB(){
        AppDatabase db = Room.databaseBuilder(getApplicationContext(),AppDatabase.class,"pokemons").allowMainThreadQueries().build();
        java.util.List<Pokemon> items = db.pokemonDAO().getAll();
        Assert.assertEquals(items.size(),0);
        Pokemon pokemon = new Pokemon("Pika",null);
        db.pokemonDAO().insertAll(pokemon);
        Assert.assertEquals(items.size(),1);
        db.pokemonDAO().nukeTable();
        Assert.assertEquals(items.size(),0);
    }
}
