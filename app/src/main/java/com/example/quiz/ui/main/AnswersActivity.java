package com.example.quiz.ui.main;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.quiz.Pokemon;
import com.example.quiz.R;
import com.example.quiz.ViewModel.PokemonViewModel;

import java.util.List;

public class AnswersActivity extends AppCompatActivity {


    private PokemonViewModel viewModel;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answers);

        final PokemonListAdapter adapter = new PokemonListAdapter();

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        recyclerView.setAdapter(adapter);


        viewModel = ViewModelProviders.of(this).get(PokemonViewModel.class);

        observerSetup();

    }

    private void observerSetup() {

        final PokemonListAdapter adapter = new PokemonListAdapter();

        viewModel.getAllPokemons().observe(this,
                new Observer<List<Pokemon>>() {
                    @Override
                    public void onChanged(@Nullable final List<Pokemon> p) {
                        adapter.setPokemons(p);
                    }
                });

    }
        public void exitButton() {
            Button btnExit = findViewById(R.id.exit);
            btnExit.setOnClickListener(view -> {
                Intent intent = new Intent(AnswersActivity.this, new MainActivity().getClass());
                startActivity(intent);
                finish();
            });
        }




}