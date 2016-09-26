package com.example.jonguk.andrsamplenetwork.network;

import com.example.jonguk.andrsamplenetwork.json.UserJson;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Jonguk on 2016. 9. 26..
 */

public interface GithubUserService {
    @GET("/users/{username}")
    Call<UserJson> getUser(@Path("username") String username);
}
