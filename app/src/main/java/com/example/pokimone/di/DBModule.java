package com.example.pokimone.di;

import android.app.Application;

import androidx.room.Room;

import com.example.pokimone.db.PokiDAO;
import com.example.pokimone.db.PokiFavDB;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public class DBModule {

    @Provides
    @Singleton
    public static PokiFavDB getDB(Application application){
        return Room.databaseBuilder(application,PokiFavDB.class,"favDB")
                .fallbackToDestructiveMigration()
                .build();
    }


    @Provides
    @Singleton
    public static PokiDAO getDAO(PokiFavDB pokiFavDB){
        return pokiFavDB.getPokiDoa();
    }
}
