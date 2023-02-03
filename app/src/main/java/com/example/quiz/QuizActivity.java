package com.example.quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Pair;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.*;

public class QuizActivity extends AppCompatActivity {

    int score = 0;
    int answered = 0;

    private ArrayList<Pair<Bitmap, String>> questions;

    private ImageView imageView;

    private Button btn1;
    private Button btn2;
    private Button btn3;

    private void displayNextQuestion() {

        // Checks if the user has answered all the questions in the list
        if (answered == questions.size()) {
            Toast.makeText(QuizActivity.this, "All questions answered!", Toast.LENGTH_SHORT).show();
            return;
        }

        // Set the imageView to be the first element in the question array
        imageView.setImageBitmap(questions.get(answered).first);

        // The correct answer
        String correctAnswer = questions.get(answered).second;
        System.out.println("Question order: 1: " + questions.get(0).second + ", 2: " + questions.get(1).second + ", 3:" + questions.get(2).second);
        System.out.println("Current question: (" + (answered+1) + ") " + questions.get(answered).second);
        System.out.println("Current answer: " + correctAnswer);

        // Get two wrong answers at random
        ArrayList<String> wrongAnswers = new ArrayList<>();

        ArrayList<Pair<Bitmap, String>> shuffled = new ArrayList<>(questions);
        Collections.shuffle(shuffled);

        for (Pair<Bitmap, String> p : shuffled) {
            if (!p.second.equals(correctAnswer)) {
                wrongAnswers.add(p.second);
                if (wrongAnswers.size() == 2) break;
            }
        }

        // Store buttons in arraylist and shuffle
        ArrayList<Button> buttons = new ArrayList<>( Arrays.asList(btn1, btn2, btn3));
        Collections.shuffle(buttons);

        // Set the correct answer button
        buttons.get(0).setText(correctAnswer);
        buttons.get(0).setOnClickListener(view -> {
            score++;
            answered++;
            displayNextQuestion();
        });

        // Set the wrong answer buttons
        for (int i = 1; i <= wrongAnswers.size(); i++) {
            buttons.get(i).setText(wrongAnswers.get(i-1));
            buttons.get(i).setOnClickListener(view -> {
                answered++;
                displayNextQuestion();
            });
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        // Fetch questions
        questions = AnswersActivity.getQuestions();

        //Shuffle the questions to preserve randomness
        Collections.shuffle(questions);
        System.out.println("SHUFFLED");

        // Setup imageView and buttons
        imageView = findViewById(R.id.imageView);

        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);

        displayNextQuestion();
    }
}