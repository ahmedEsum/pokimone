package com.example.pokimone.viewmodels;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.pokimone.model.PokimaneCharacter;
import com.example.pokimone.model.PokimoneWholeRes;
import com.example.pokimone.repository.Repository;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.schedulers.Schedulers;

@HiltViewModel
public class PokiListViewModel extends ViewModel {

    private static final String TAG = "PokiListViewModel";
    private Repository repository;
    MutableLiveData<ArrayList<PokimaneCharacter>> pokiList = new MutableLiveData<>();
    MutableLiveData<List<PokimaneCharacter>> favList = new MutableLiveData<>();


    @Inject
    public PokiListViewModel(Repository repository) {
        this.repository = repository;
    }

    public void getPoki() {
        repository.getApiList().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Function<PokimoneWholeRes, ArrayList<PokimaneCharacter>>() {
                    @Override
                    public ArrayList<PokimaneCharacter> apply(PokimoneWholeRes pokimoneWholeRes) throws Throwable {
                        ArrayList<PokimaneCharacter> list = pokimoneWholeRes.getResults();
                        for (PokimaneCharacter pokimaneCharacter : list) {
                            String url = pokimaneCharacter.getUrl();
                            String[] url_array = url.split("/");
                            pokimaneCharacter.setUrl("https://cdn.traction.one/pokedex/pokemon/" + url_array[url_array.length - 1] + ".png");
                        }
                        return list;
                    }
                })
                .subscribe(result -> pokiList.setValue(result)
                        , error -> Log.d(TAG, "getPoki: " + error));
    }

    public MutableLiveData<ArrayList<PokimaneCharacter>> getPokiList() {
        return pokiList;
    }

    public MutableLiveData<List<PokimaneCharacter>> getFavList() {
        return favList;
    }

    public void insertPokiChar(PokimaneCharacter pokimaneCharacter) {
        repository.insertPokiChar(pokimaneCharacter);
    }

    public void deletePokiFavChar(String pokiName) {

        repository.deletePokiFavChar(pokiName);
    }


    public void getPokiFavList() {

        repository.getPokiFavList()
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result->favList.setValue(result),error-> Log.d(TAG, "getPokiFavList: "+error));
    }



}
