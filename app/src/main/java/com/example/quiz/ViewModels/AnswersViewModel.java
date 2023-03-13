package com.example.quiz.ViewModels;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.quiz.Pokemon;
import com.example.quiz.PokemonRepository;

import java.util.List;

public class AnswersViewModel extends AndroidViewModel {


    private PokemonRepository repository;
    private LiveData<List<Pokemon>> allPokemons;

    public AnswersViewModel (Application application) {
        super(application);
        repository = new PokemonRepository(application);
        allPokemons = repository.getAllPokemons();
    }

    public LiveData<List<Pokemon>> getAllPokemons() {
        return allPokemons;
    }

    public void insertProduct(Pokemon pokemon) {
        repository.insertPokemon(pokemon);
    }


    }



