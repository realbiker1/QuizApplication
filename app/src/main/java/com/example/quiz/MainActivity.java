package com.example.quiz;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
 public static boolean initialized = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    if(!initialized) {
        // Initialize question list, can be accessed regardless of what activity started first
        Bitmap q3 = BitmapFactory.decodeResource(getResources(), R.drawable.bulbasaur);
        Bitmap q2 = BitmapFactory.decodeResource(getResources(), R.drawable.charmander);
        Bitmap q1 = BitmapFactory.decodeResource(getResources(), R.drawable.marill);
        AnswersActivity.initializeQuestions(q1, q2, q3);
        initialized = true;
    }

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
}