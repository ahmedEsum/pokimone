package com.example.pokimone.db;


import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import com.example.pokimone.model.PokimaneCharacter;
import java.util.List;
import io.reactivex.rxjava3.core.Single;

@Dao
public interface PokiDAO {

    //insert
    @Insert
    void insertPoki(PokimaneCharacter pokimaneCharacter);
    //query
    @Query("SELECT * FROM fav_table ")
    Single<List<PokimaneCharacter>> getFavPokiList();

    //delete
    @Query("DELETE FROM fav_table WHERE name =:pokimaneCharacter ")
    void deletePoki(String pokimaneCharacter);
}
