package com.example.quiz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Pair;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;

public class AnswersActivity extends AppCompatActivity {

    public static ArrayList<Pair<Bitmap, String>> questions = new ArrayList<>();

    public static ArrayList<Pair<Bitmap, String>> getQuestions() {
        return questions;
    }

    public static void addQuestion(Bitmap image, String ansText) {
        questions.add(new Pair<Bitmap, String>(image, ansText));
    }
    public static void initializeQuestions(Bitmap q1, Bitmap q2, Bitmap q3) {
        addQuestion(q3, "bulbasaur");
        addQuestion(q2, "charmander");
        addQuestion(q1, "marill");
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answers);
        AppDatabase db = Room.databaseBuilder
                (getApplicationContext(),AppDatabase.class,"pokemons").allowMainThreadQueries().build();

        //To do
      /*  Button sortBtn = findViewById(R.id.Sort);
        sortBtn.setOnClickListener(view -> {
            Collections.reverse(questions);
            setPicturesAndAnswers(questions);
        });*/



        sortQuestions();
        setPicturesAndAnswers(questions);
        exitButton();
    }
    public void setPicturesAndAnswers(ArrayList<Pair<Bitmap, String>> pairs) {
        LinearLayout container = findViewById(R.id.ansContainer);
        for (Pair<Bitmap, String> imageData : pairs) {
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
        }
    }
    private void sortQuestions() {
        ArrayList<Pair<Bitmap,String>> sortedQuestions = new ArrayList<>();
        ArrayList<String> sortedNames = new ArrayList<>();
        for(Pair<Bitmap,String> q : questions){
            sortedNames.add(q.second);
        }
        Collections.sort(sortedNames);
            for (String value : sortedNames) {
                for(int i=0; i<questions.size();i++){
                    if (questions.get(i).second.equals(value)) {
                    sortedQuestions.add(questions.get(i));
                    }
                }
            }
        questions = sortedQuestions;
    }

    public void exitButton(){
        Button btnExit = findViewById(R.id.exit);
        btnExit.setOnClickListener(view -> {
            Intent intent = new Intent(AnswersActivity.this, new MainActivity().getClass());
            startActivity(intent);
            finish();
        });
    }

}