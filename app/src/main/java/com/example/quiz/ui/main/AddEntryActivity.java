package com.example.quiz.ui.main;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
<<<<<<< HEAD:app/src/main/java/com/example/quiz/ui/main/AddEntryActivity.java
=======
import androidx.core.content.res.ResourcesCompat;
>>>>>>> c31df40bc3989f080da1881075ac4cc1a9115c55:app/src/main/java/com/example/quiz/AddEntryActivity.java
import androidx.room.Room;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

<<<<<<< HEAD:app/src/main/java/com/example/quiz/ui/main/AddEntryActivity.java
import com.example.quiz.AppDatabase;
import com.example.quiz.Pokemon;
import com.example.quiz.R;

import java.io.IOException;
=======
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
>>>>>>> c31df40bc3989f080da1881075ac4cc1a9115c55:app/src/main/java/com/example/quiz/AddEntryActivity.java
import java.nio.ByteBuffer;
import java.util.List;

public class AddEntryActivity extends AppCompatActivity {

    Bitmap picture;
    EditText answerText;
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
    private static final int CAMERA_REQUEST = 1888;

    private void newPhoto() {
        Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(cameraIntent, CAMERA_REQUEST);
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            imageView.setImageBitmap(photo);
            picture = photo;
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

    void submitEntry() {
        String answer = answerText.getText().toString();
        System.out.println("Added image with answer " + answer);
        if (picture != null && !answer.equals("")) {
            Pokemon pokemon = new Pokemon(answer,convertBitmapToByte(picture));
            AppDatabase db = Room.databaseBuilder(getApplicationContext(),AppDatabase.class,"pokemons").allowMainThreadQueries().build();
            db.pokemonDAO().insertAll(pokemon);
            db.pokemonDAO().updateUsers(pokemon);

<<<<<<< HEAD:app/src/main/java/com/example/quiz/ui/main/AddEntryActivity.java
                List<Pokemon> pokemonList = db.pokemonDAO().getAll();
                for(Pokemon a : pokemonList){
                    System.out.println("Navn: " + a.getName());
                    AnswersActivity.addQuestion(picture, answer);

                }
            System.out.println("POKEMON: " + db.pokemonDAO().find(answer));
            System.out.println("Poke ans: " + db.pokemonDAO().getAll());
=======
>>>>>>> c31df40bc3989f080da1881075ac4cc1a9115c55:app/src/main/java/com/example/quiz/AddEntryActivity.java
        }
        finish();
    }

    public Bitmap convertByteToBitmap(byte [] picture){
        InputStream targetStream = new ByteArrayInputStream(picture);

        return BitmapFactory.decodeStream(targetStream);
    }
    public byte [] convertBitmapToByte(Bitmap bitmap){
        int size = bitmap.getRowBytes() * bitmap.getHeight();
        ByteBuffer byteBuffer = ByteBuffer.allocate(size);
        bitmap.copyPixelsToBuffer(byteBuffer);
        return byteBuffer.array();
    }
}