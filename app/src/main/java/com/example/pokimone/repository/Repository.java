package com.example.pokimone.repository;

import android.util.Log;
import com.example.pokimone.db.PokiDAO;
import com.example.pokimone.model.PokimaneCharacter;
import com.example.pokimone.model.PokimoneWholeRes;
import com.example.pokimone.network.PokimaneApiResponse;
import java.util.List;
import javax.inject.Inject;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleOnSubscribe;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class Repository {
    private static final String TAG = "Respository";
    private PokimaneApiResponse pokimaneApiResponse;
    private PokiDAO pokiDAO;

    @Inject
    public Repository(PokimaneApiResponse pokimaneApiResponse, PokiDAO pokiDAO) {
        this.pokimaneApiResponse = pokimaneApiResponse;
        this.pokiDAO = pokiDAO;
    }

    public Observable<PokimoneWholeRes> getApiList() {
        return pokimaneApiResponse.getPokimaneResponse();
    }

    public void insertPokiChar(PokimaneCharacter pokimaneCharacter) {
        Single.create((SingleOnSubscribe<PokimaneCharacter>) emitter -> emitter.onSuccess(pokimaneCharacter)).subscribeOn(Schedulers.io())
                .subscribe(result -> pokiDAO.insertPoki(result), error -> Log.d(TAG, "deletePokiFavChar: " + error));

    }

    public void deletePokiFavChar(String pokiName) {

        Single.create((SingleOnSubscribe<String>) emitter -> emitter.onSuccess(pokiName))
                .subscribeOn(Schedulers.computation())
                .subscribe(result -> pokiDAO.deletePoki(pokiName), error -> Log.d(TAG, "deletePokiFavChar: " + error));

    }

    public Single<List<PokimaneCharacter>> getPokiFavList() {

        return pokiDAO.getFavPokiList();
    }
}
