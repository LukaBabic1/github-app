package com.undabot.babic.data.network.model;

import com.google.gson.annotations.SerializedName;

public final class ApiCodeRepository {

    @SerializedName("id")
    public int id;

    @SerializedName("name")
    public String name;

    @SerializedName("full_name")
    public String fullName;

    @SerializedName("owner")
    public ApiUser owner;

    @SerializedName("description")
    public String description;

    @SerializedName("url")
    public String repositoryUrl;

    @SerializedName("html_url")
    public String homepageUrl;

    @SerializedName("language")
    public String language;

    @SerializedName("created_at")
    public String createdAt;

    @SerializedName("updated_at")
    public String updatedAt;

    @SerializedName("pushed_at")
    public String pushedAt;

    @SerializedName("stargazers_count")
    public int stargazersCount;

    @SerializedName("watchers_count")
    public int watchersCount;

    @SerializedName("forks_count")
    public int forksCount;

    @SerializedName("open_issues_count")
    public int openIssuesCount;

    @SerializedName("score")
    public float score;

    @SerializedName("private")
    public boolean isPrivate;

    @SerializedName("has_issues")
    public boolean hasIssues;

    @SerializedName("has_projects")
    public boolean hasProjects;

    @SerializedName("has_wiki")
    public boolean hasWiki;
}