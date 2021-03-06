package com.undabot.babic.data.network.service;

import com.undabot.babic.data.network.model.ApiCodeRepository;
import com.undabot.babic.data.network.model.ApiRevokeTokenResponse;
import com.undabot.babic.data.network.model.ApiSearchRepositoriesResponse;
import com.undabot.babic.data.network.model.ApiUser;

import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Single;

public interface GitHubService {

    @GET("/user")
    Single<ApiUser> getCurrentUser(@Header("Authorization") String authorization);

    @GET("/users/{username}")
    Single<ApiUser> getUser(@Path("username") String username);

    @GET("/users/{username}")
    Single<ApiUser> getUser(@Header("Authorization") String authorization, @Path("username") String username);

    @GET("/search/repositories")
    Single<ApiSearchRepositoriesResponse> searchRepositories(@Query("q") String query, @Query("sort") String sort, @Query("per_page") int perPage, @Query("page") int page);

    @GET("/search/repositories")
    Single<ApiSearchRepositoriesResponse> searchRepositories(@Header("Authorization") String authorization, @Query("q") String query, @Query("sort") String sort,
                                                             @Query("page") int page, @Query("per_page") int perPage);

    @GET("/repos/{username}/{repositoryName}")
    Single<ApiCodeRepository> getRepositoryDetails(@Path("username") String username, @Path("repositoryName") String repositoryName);

    @GET("/repos/{username}/{repositoryName}")
    Single<ApiCodeRepository> getRepositoryDetails(@Header("Authorization") String authorization, @Path("username") String username, @Path("repositoryName") String repositoryName);

    @DELETE("/applications/{client_id}/grants/{auth_token}")
    Single<ApiRevokeTokenResponse> logOut(@Header("Authorization") String basicAuthorization, @Path("client_id") String clientId, @Path("auth_token") String authToken);
}
