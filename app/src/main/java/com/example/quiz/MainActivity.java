package com.example.quiz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Pair;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize question list, can be accessed regardless of what activity started first
        Bitmap q1 = BitmapFactory.decodeResource(getResources(),R.drawable.bulbasaur);
        Bitmap q2 = BitmapFactory.decodeResource(getResources(),R.drawable.charmander);
        Bitmap q3 = BitmapFactory.decodeResource(getResources(),R.drawable.marill);
        AnswersActivity.initializeQuestions(q1, q2, q3);

        // Launches the quiz activity
        Button quizBtn = findViewById(R.id.quizBtn);
        quizBtn.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, QuizActivity.class);
          startActivity(intent);
        });

        // Launches the Answers activity
        Button answersBtn = findViewById(R.id.answersBtn);
        answersBtn.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, AnswersActivity.class);
            startActivity(intent);
        });

        // Launches the Add entry activity
        Button addEntryBtn = findViewById(R.id.addEntryBtn);
        addEntryBtn.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, AddEntryActivity.class);
            startActivity(intent);
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.example_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intentAnswers = new Intent(MainActivity.this, AnswersActivity.class);
        Intent intentQuiz = new Intent(MainActivity.this, QuizActivity.class);
        Intent intentMain = new Intent(MainActivity.this, MainActivity.class);
        Intent intentAdd = new Intent(MainActivity.this, AddEntryActivity.class);
        switch (item.getItemId()){
            case R.id.addQuestions:
                startActivity(intentAdd);
                return true;
            case R.id.showAnswers:
                startActivity(intentAnswers);
                return true;
            case R.id.easyQuiz:
                intentQuiz.putExtra("choice",false);
                 startActivity(intentQuiz);
                return true;
            case R.id.hardQuiz:
                intentQuiz.putExtra("choice",true);
                startActivity(intentQuiz);
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}