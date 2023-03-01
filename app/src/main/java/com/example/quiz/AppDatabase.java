package com.example.quiz;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.Room;

@Database(entities = Pokemon.class, version = 1 ,exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    public abstract PokemonDAO pokemonDAO();
}
