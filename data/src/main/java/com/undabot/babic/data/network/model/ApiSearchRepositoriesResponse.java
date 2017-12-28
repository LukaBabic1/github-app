package com.undabot.babic.data.network.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public final class ApiSearchRepositoriesResponse {

    @SerializedName("total_count")
    public int totalCount;

    @SerializedName("incomplete_results")
    public boolean incompleteResults;

    @SerializedName("items")
    public List<ApiCodeRepository> codeRepositories;
}
