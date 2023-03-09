package com.example.quiz;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

public class PokemonRepository {
    private MutableLiveData<List<Pokemon>> searchResults =
            new MutableLiveData<>();
    private List<Pokemon> allPokemons;
    private PokemonDAO pokemonDAO;

    public List<Pokemon> getAllPokemons() {
        return allPokemons;
    }

    public MutableLiveData<List<Pokemon>> getSearchResults() {
        return searchResults;
    }

    public PokemonRepository(Application application) {
        AppDatabase db;
        db = AppDatabase.getDatabase(application);
        pokemonDAO = db.pokemonDAO();
        allPokemons = pokemonDAO.getAll();
    }

    public void insertPokemon(Pokemon pokemon) {
        InsertAsyncTask task = new InsertAsyncTask(pokemonDAO);
        task.execute(pokemon);
    }

    public void deletePokemon(Pokemon pokemon) {
        DeleteAsyncTask task = new DeleteAsyncTask(pokemonDAO);
        task.execute(pokemon);
    }

    public void findPokemon(String name) {
        QueryAsyncTask task = new QueryAsyncTask(pokemonDAO);
        task.delegate = this;
        task.execute(name);
    }

    private void asyncFinished(List<Pokemon> results) {
        searchResults.setValue(results);
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

        @Override
        protected void onPostExecute(List<Pokemon> result) {
            delegate.asyncFinished(result);
        }
    }

    private static class InsertAsyncTask extends AsyncTask<Pokemon, Void, Void> {

        private PokemonDAO asyncTaskDao;

        InsertAsyncTask(PokemonDAO dao) {
            asyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Pokemon... params) {
            asyncTaskDao.insertAll(params[0]);
            return null;
        }
    }

    private static class DeleteAsyncTask extends AsyncTask<Pokemon, Void, Void> {

        private PokemonDAO asyncTaskDao;

        DeleteAsyncTask(PokemonDAO dao) {
            asyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Pokemon... params) {
            asyncTaskDao.delete(params[0]);
            return null;
        }
    }
}
