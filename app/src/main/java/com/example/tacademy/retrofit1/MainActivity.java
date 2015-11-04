package com.example.tacademy.retrofit1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        NetworkManager networkManager = NetworkManager.getInstance();
        Call<List<Bicycle>> result = networkManager.getBicycle();
        result.enqueue(new Callback<List<Bicycle>>() {
            @Override
            public void onResponse(Response<List<Bicycle>> response, Retrofit retrofit) {
                List<Bicycle> bicycle = response.body();
                for(Bicycle bicycle1:bicycle)
                    Log.i("result",bicycle1.toString());
            }

            @Override
            public void onFailure(Throwable t) {
                Log.e("aa","aa");
            }
        });

        /*
        Call<List<NetworkManager.Contributor>> result = networkManager.getContributors();
        result.enqueue(new Callback<List<NetworkManager.Contributor>>() {
            @Override
            public void onResponse(Response<List<NetworkManager.Contributor>> response, Retrofit retrofit) {
                List<NetworkManager.Contributor> contributors = response.body();
                for (NetworkManager.Contributor contributor : contributors) {
                        Log.i("result",contributor.login + " (" + contributor.contributions + ")");
                }
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });*/
    }
}
