package com.undabot.babic.data.network.service;

import com.undabot.babic.data.network.model.ApiUser;

import retrofit2.http.Header;
import rx.Single;

public interface GitHubService {

    Single<ApiUser> getUser(@Header("Authorization") String authorization);
}
