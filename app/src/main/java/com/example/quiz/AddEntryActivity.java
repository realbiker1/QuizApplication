package com.example.quiz;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Camera;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class AddEntryActivity extends AppCompatActivity {

    private static final int REQUEST_IMAGE_CAPTURE = 1888;
    private Bitmap picture;
    private EditText answerText;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_entry);

        imageView = findViewById(R.id.image_view);

        Button existingPhotoButton = (Button) findViewById(R.id.existing_photo_button);
        existingPhotoButton.setOnClickListener(view -> existingPhoto());

        Button newPhotoButton = (Button) findViewById(R.id.new_photo_button);
        newPhotoButton.setOnClickListener(view -> newPhoto());

        answerText = (EditText) findViewById(R.id.answerEntry);

        Button submit = findViewById(R.id.submit);
        submit.setOnClickListener(view -> submitEntry());
        exitButton();
    }
    public void exitButton(){
        Button btnExit = findViewById(R.id.exit);
        btnExit.setOnClickListener(view -> {
            Intent intent = new Intent(AddEntryActivity.this, new MainActivity().getClass());
            startActivity(intent);
        });
    }

    private void existingPhoto() {
        Intent i = new Intent();
        i.setType("image/*");
        i.setAction(Intent.ACTION_GET_CONTENT);
        getPhoto.launch(i);
    }

    private void newPhoto() {

        //Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        //i.setType("image/*");
        //i.setAction(Intent.ACTION_GET_CONTENT);
        //getPhoto.launch(i);

        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        try {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        } catch (ActivityNotFoundException e) {
            // display error state to the user
        }


    }

    ActivityResultLauncher<Intent> getPhoto = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(), result -> {
                if (result.getResultCode() == Activity.RESULT_OK) {
                    Intent data = result.getData();

                    if (data != null && data.getData() != null) {
                        Uri uri = data.getData();
                        System.out.println("Data got");
                        try {
                            picture = BitmapFactory.decodeStream(
                                    getContentResolver().openInputStream(uri)
                            );
                            imageView.setImageBitmap(picture);

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });

    private void submitEntry() {
        String answer = answerText.getText().toString();
        System.out.println("Added image with answer " + answer);
        if (picture != null && !answer.equals("")) {
            AnswersActivity.addQuestion(picture, answer);
        }
        finish();

    }
}