package com.example.jonguk.andrsamplenetwork.network;

import com.example.jonguk.andrsamplenetwork.json.ReposJson;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Jonguk on 2016. 9. 26..
 */

public interface GithubReposService {
    @GET("/users/{username}/repos")
    Call<List<ReposJson>> getRepos(@Path("username") String username);
}
