package com.example.pokimone.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.pokimone.model.PokimaneCharacter;


@Database(entities = PokimaneCharacter.class,version = 1,exportSchema = false)
public abstract class PokiFavDB extends RoomDatabase {

    public abstract PokiDAO getPokiDoa();
}
