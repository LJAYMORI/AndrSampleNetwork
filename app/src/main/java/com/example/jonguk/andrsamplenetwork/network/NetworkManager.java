package com.example.jonguk.andrsamplenetwork.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Jonguk on 2016. 9. 26..
 */

public class NetworkManager {
    private static Retrofit sInstance;
    private NetworkManager(){}
    public static Retrofit getInstance() {
        if (sInstance == null) {
            sInstance = new Retrofit.Builder()
                    .baseUrl("https://api.github.com")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return sInstance;
    }
}
