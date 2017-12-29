package com.undabot.babic.data.network.model;

import com.google.gson.annotations.SerializedName;

public final class ApiUser {

    @SerializedName("id")
    public int id;

    @SerializedName("login")
    public String username;

    @SerializedName("avatarUrl")
    public String avatarUrl;

    @SerializedName("siteHtmlUrl")
    public String siteHtmlUrl;

    @SerializedName("name")
    public String name;

    @SerializedName("type")
    public String type;

    @SerializedName("company")
    public String companyName;

    @SerializedName("location")
    public String location;

    @SerializedName("email")
    public String email;

    @SerializedName("site_admin")
    public boolean siteAdmin;

    @SerializedName("hireable")
    public String hireable;

    @SerializedName("followers")
    public int followers;

    @SerializedName("following")
    public int following;

    @SerializedName("createdAt")
    public String createdAt;

    @SerializedName("updatedAt")
    public String updatedAt;

    @SerializedName("privateGists")
    public int privateGists;

    @SerializedName("publicRepos")
    public int publicRepos;

    @SerializedName("publicGists")
    public int publicGists;

    @SerializedName("totalPrivateRepos")
    public int totalPrivateRepos;

    @SerializedName("ownedPrivateRepos")
    public int ownedPrivateRepos;
}

/*
    "login": "LukaBabic1",
            "id": 4068512,
            "avatar_url": "https://avatars0.githubusercontent.com/u/4068512?v=4",
            "gravatar_id": "",
            "url": "https://api.github.com/users/LukaBabic1",
            "html_url": "https://github.com/LukaBabic1",
            "followers_url": "https://api.github.com/users/LukaBabic1/followers",
            "following_url": "https://api.github.com/users/LukaBabic1/following{/other_user}",
            "gists_url": "https://api.github.com/users/LukaBabic1/gists{/gist_id}",
            "starred_url": "https://api.github.com/users/LukaBabic1/starred{/owner}{/repo}",
            "subscriptions_url": "https://api.github.com/users/LukaBabic1/subscriptions",
            "organizations_url": "https://api.github.com/users/LukaBabic1/orgs",
            "repos_url": "https://api.github.com/users/LukaBabic1/repos",
            "events_url": "https://api.github.com/users/LukaBabic1/events{/privacy}",
            "received_events_url": "https://api.github.com/users/LukaBabic1/received_events",
            "type": "User",
            "site_admin": false,
            "name": "Luka Babić",
            "company": "Five",
            "blog": "",
            "location": "Zagreb",
            "email": null,
            "hireable": null,
            "bio": null,
            "public_repos": 7,
            "public_gists": 0,
            "followers": 0,
            "following": 0,
            "created_at": "2013-04-05T11:56:55Z",
            "updated_at": "2017-12-08T18:27:44Z",
            "private_gists": 0,
            "total_private_repos": 0,
            "owned_private_repos": 0,
            "disk_usage": 985,
            "collaborators": 0,
            "two_factor_authentication": false,
*/