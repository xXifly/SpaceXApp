package com.alexandreexample.spacexapp.Controls;

import com.alexandreexample.spacexapp.Models.Launch;
import com.alexandreexample.spacexapp.Models.Rocket;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GithubService {

    public static final String ENDPOINT = "https://api.spacexdata.com";

    @GET("/v3/rockets")
    Call<List<Rocket>> getRockets();

    @GET("/v3/rockets/{rocketId}")
    Call<Rocket> getRocket(@Path("rocketId") String rocketId);

    @GET("/v3/launches")
    Call<List<Launch>> getLaunches();

    @GET("/v3/launches/{flightNumber}")
    Call<Launch> getLaunch(@Path("flightNumber") int flightNumber);

    GithubService githubService = new Retrofit.Builder()
            .baseUrl(GithubService.ENDPOINT)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(GithubService.class);

}
