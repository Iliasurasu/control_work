package com.example.example_retrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    @GET("photos")
    Call<List<Photo>> getPhotos();
}
