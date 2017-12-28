package com.undabot.babic.data.network.service;

import com.undabot.babic.data.network.model.ApiCodeRepository;
import com.undabot.babic.data.network.model.ApiUser;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import rx.Single;

public interface GitHubService {

    @GET("/users/{username}")
    Single<ApiUser> getUser(@Header("Authorization") String authorization, @Path("username") String username);

    @GET("/search/repositories")
    Single<List<ApiCodeRepository>> searchRepositories(@Header("Authorization") String authorization);
}
