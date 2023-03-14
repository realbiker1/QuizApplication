package com.example.quiz.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.quiz.Pokemon;
import com.example.quiz.PokemonRepository;

import java.util.List;

public class PokemonViewModel extends AndroidViewModel {


    private PokemonRepository repository;
    private LiveData<List<Pokemon>> allPokemons;

    public PokemonViewModel(@NonNull Application application) {
        super(application);
        repository = new PokemonRepository(application);
        allPokemons = repository.getAllPokemons();
    }

    public LiveData<List<Pokemon>> getAllPokemons() {
        return allPokemons;
    }

    public void insertPokemon(Pokemon pokemon) {
        repository.insert(pokemon);
    }
    public void deletePokemon(Pokemon pokemon) { repository.delete(pokemon);}

    public  void updatePokemon(Pokemon pokemon) {repository.update(pokemon);}

    public void nuke() {repository.deleteAllPokemons();}

    }



