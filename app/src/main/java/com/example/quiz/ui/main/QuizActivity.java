package com.example.quiz.ui.main;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Pair;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.quiz.R;

import java.util.*;

public class QuizActivity extends AppCompatActivity {
    public boolean choice = false;
    public CountDownTimer timer;
    int score = 0;
    int answered = 0;

    private ArrayList<Pair<Bitmap, String>> questions;

    private ImageView imageView;

    private Button btn1;
    private Button btn2;
    private Button btn3;

public void returnToMainMenu(){
        Intent intent = new Intent(QuizActivity.this, new MainActivity().getClass());
        startActivity(intent);
        finish();
}
    public void exitButton(){
        Button btnExit = findViewById(R.id.exit);
        btnExit.setOnClickListener(view -> {
            Intent intent = new Intent(QuizActivity.this, new MainActivity().getClass());
            startActivity(intent);
            finish();
        });
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        Bundle values = getIntent().getExtras();
        try {
            choice = values.getBoolean("choice");
        } catch (NullPointerException e) {
            e.printStackTrace();
        }

        // Fetch questions

        //Shuffle the questions to preserve randomness
        Collections.shuffle(questions);
        System.out.println("SHUFFLED");

        // Setup imageView and buttons
        imageView = findViewById(R.id.imageView);

        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
 }
}