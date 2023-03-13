package com.example.quiz.ui.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.os.Bundle;

<<<<<<< HEAD:app/src/main/java/com/example/quiz/ui/main/MainActivity.java
import com.example.quiz.AppDatabase;
import com.example.quiz.R;
=======
import java.io.ByteArrayInputStream;
import java.io.InputStream;
>>>>>>> c31df40bc3989f080da1881075ac4cc1a9115c55:app/src/main/java/com/example/quiz/MainActivity.java

public class MainActivity extends AppCompatActivity {
 public static boolean initialized = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AppDatabase db = Room.databaseBuilder
                (getApplicationContext(),AppDatabase.class,"pokemons").allowMainThreadQueries().build();
    if(!initialized) {
        // Initialize question list, can be accessed regardless of what activity started first
        Bitmap q3 = BitmapFactory.decodeResource(getResources(), R.drawable.bulbasaur);
        Bitmap q2 = BitmapFactory.decodeResource(getResources(), R.drawable.charmander);
        Bitmap q1 = BitmapFactory.decodeResource(getResources(), R.drawable.marill);

<<<<<<< HEAD:app/src/main/java/com/example/quiz/ui/main/MainActivity.java


=======
        AppDatabase db = Room.databaseBuilder(getApplicationContext(),AppDatabase.class,"pokemons").allowMainThreadQueries().build();
        db.pokemonDAO().nukeTable();
>>>>>>> c31df40bc3989f080da1881075ac4cc1a9115c55:app/src/main/java/com/example/quiz/MainActivity.java
        AnswersActivity.initializeQuestions(q1, q2, q3);
        initialized = true;
    }

        // Launches the quiz activity
        Button quizBtn = findViewById(R.id.quizBtn);
        quizBtn.setOnClickListener(view -> {
            AppDatabase db = Room.databaseBuilder(getApplicationContext(),AppDatabase.class,"pokemons").allowMainThreadQueries().build();
            for(Pokemon x : db.pokemonDAO().getAll()){
                AnswersActivity.addQuestion(convertByteToBitmap(x.getBitmap()),x.getName());
            }
            Intent intent = new Intent(MainActivity.this, QuizActivity.class);
            intent.putExtra("choice",false);
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
    public Bitmap convertByteToBitmap(byte [] picture){
        InputStream targetStream = new ByteArrayInputStream(picture);

        return BitmapFactory.decodeStream(targetStream);
    }
}