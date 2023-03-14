package com.example.quiz;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

public class PokemonRepository {
    private PokemonDAO pokemonDao;
    private LiveData<List<Pokemon>> allPokemons;

    public PokemonRepository(Application application) {
        AppDatabase database = AppDatabase.getDatabase(application);
        pokemonDao = database.pokemonDAO();
        allPokemons = pokemonDao.getAllPokemons();
    }

    public void insert(Pokemon p) {
        new InsertPokemonAsyncTask(pokemonDao).execute(p);
    }

    public void update(Pokemon p) {
        new UpdatePokemonAsyncTask(pokemonDao).execute(p);
    }

    public void delete(Pokemon p) {

        new DeletePokemonAsyncTask(pokemonDao).execute(p);
    }

    public void deleteAllPokemons() {

        new DeleteAllPokemonAsyncTask(pokemonDao).execute();
    }
    public void findPokemon(String name) {
        QueryAsyncTask task = new QueryAsyncTask(pokemonDao);
        task.delegate = this;
        task.execute(name);
    }

    public LiveData<List<Pokemon>> getAllPokemons() {
        return allPokemons;
    }

    private static class InsertPokemonAsyncTask extends AsyncTask<Pokemon, Void, Void> {
        private PokemonDAO pokemonDAO;

        private InsertPokemonAsyncTask(PokemonDAO pokemonDAO) {
            this.pokemonDAO = pokemonDAO;
        }

        @Override
        protected Void doInBackground(Pokemon... p) {
            pokemonDAO.insert(p[0]);
            return null;
        }
    }

    private static class UpdatePokemonAsyncTask extends AsyncTask<Pokemon, Void, Void> {
        private PokemonDAO pokemonDAO;

        private UpdatePokemonAsyncTask(PokemonDAO pokemonDAO) {
            this.pokemonDAO = pokemonDAO;
        }

        @Override
        protected Void doInBackground(Pokemon... p) {
            pokemonDAO.update(p[0]);
            return null;
        }
    }

    private static class DeletePokemonAsyncTask extends AsyncTask<Pokemon, Void, Void> {
        private PokemonDAO pokemonDAO;

        private DeletePokemonAsyncTask(PokemonDAO pokemonDao) {
            this.pokemonDAO = pokemonDao;
        }

        @Override
        protected Void doInBackground(Pokemon... p) {
            pokemonDAO.delete(p[0]);
            return null;
        }
    }

    private static class DeleteAllPokemonAsyncTask extends AsyncTask<Void, Void, Void> {
        private PokemonDAO pokemonDAO;

        private DeleteAllPokemonAsyncTask(PokemonDAO pokemonDao) {
            this.pokemonDAO = pokemonDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            pokemonDAO.nukeTable();
            return null;
        }
    }

    private static class QueryAsyncTask extends
            AsyncTask<String, Void, List<Pokemon>> {

        private PokemonDAO asyncTaskDao;
        private PokemonRepository delegate = null;

        QueryAsyncTask(PokemonDAO dao) {
            asyncTaskDao = dao;
        }

        @Override
        protected List<Pokemon> doInBackground(final String... params) {
            return asyncTaskDao.find(params[0]);
        }
    }
    }

