package com.example.quiz;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface PokemonDAO {

    @Insert
    void insertAll(Pokemon... pokemon);

    @Delete
    void delete(Pokemon pokemon);

    @Query("SELECT * FROM pokemons")
    LiveData<List<Pokemon>> getAllPokemons();
    @Query("DELETE FROM pokemons") void nukeTable();
    @Update
    void updateUsers(Pokemon pokemon);

    @Query("SELECT * FROM pokemons WHERE name = :name")
    List<Pokemon> find(String name);
}
