package com.example.example_retrofit;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private RecyclerView recyclerView;
    private PhotoAdapter photoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        fetchPhotos();
    }

    private void fetchPhotos() {
        ApiService apiService = RetrofitClient.getClient().create(ApiService.class);
        apiService.getPhotos().enqueue(new Callback<List<Photo>>() {
            @Override
            public void onResponse(Call<List<Photo>> call, Response<List<Photo>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Photo> photos = response.body();
                    photoAdapter = new PhotoAdapter(MainActivity.this, photos);
                    recyclerView.setAdapter(photoAdapter);
                } else {
                    Toast.makeText(MainActivity.this, "Ошибка загрузки данных: " + response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Photo>> call, Throwable t) {
                Log.e(TAG, "Ошибка сети: ", t);
                Toast.makeText(MainActivity.this, "Ошибка сети: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
