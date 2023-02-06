package com.example.quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Pair;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class AnswersActivity extends AppCompatActivity {

    public static ArrayList<Pair<Bitmap, String>> questions = new ArrayList<>();

    public static ArrayList<Pair<Bitmap, String>> getQuestions() {
        return questions;
    }

    public static void addQuestion(Bitmap image, String ansText) {
        questions.add(new Pair<Bitmap, String>(image, ansText));
    }
    public static void initializeQuestions(Bitmap q1, Bitmap q2, Bitmap q3) {
        addQuestion(q1, "bulbasaur");
        addQuestion(q2, "charmander");
        addQuestion(q3, "marill");
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answers);

        LinearLayout container = findViewById(R.id.container);

        for (Pair<Bitmap, String> imageData : questions) {
            ImageView imageView = new ImageView(this);
            imageView.setImageBitmap(imageData.first);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            params.height = 300;
            params.gravity = Gravity.CENTER;
            imageView.setLayoutParams(params);

            TextView textView = new TextView(this);
            textView.setText(imageData.second.substring(0, 1).toUpperCase() + imageData.second.substring(1));
            textView.setGravity(Gravity.CENTER);
            textView.setTextSize(20f);
            textView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));

            LinearLayout layout = new LinearLayout(this);
            layout.setOrientation(LinearLayout.VERTICAL);
            layout.addView(imageView);
            layout.addView(textView);
            layout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));

            View separator = new View(this);
            separator.setBackgroundColor(Color.BLACK);
            separator.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 5));

            container.addView(layout);
            container.addView(separator);
            exitButton();
        }
    }
    public void exitButton(){
        Button btnExit = findViewById(R.id.exit);
        btnExit.setOnClickListener(view -> {
            Intent intent = new Intent(AnswersActivity.this, new MainActivity().getClass());
            startActivity(intent);
        });
    }
}