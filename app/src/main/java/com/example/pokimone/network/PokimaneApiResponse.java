package com.example.pokimone.network;

import com.example.pokimone.model.PokimoneWholeRes;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;

public interface PokimaneApiResponse {
    @GET("pokemon")
    Observable<PokimoneWholeRes> getPokimaneResponse();
}
