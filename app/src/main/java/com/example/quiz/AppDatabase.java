package com.example.quiz;

import android.content.Context;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.Room;

@Database(entities = Pokemon.class, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract PokemonDAO pokemonDAO();
    private static AppDatabase INSTANCE;
    public static AppDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE =
                            Room.databaseBuilder(context.getApplicationContext(),
                                    AppDatabase.class,
                                    "pokemons").build();
                }
            }
        }
        return INSTANCE;
    }

}
