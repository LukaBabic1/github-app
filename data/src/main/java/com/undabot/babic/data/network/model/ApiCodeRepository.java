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

/*
    Payload example:

{
        id: 5279091,
        name: "dagger",
        full_name: "square/dagger",
        owner: {
        login: "square",
        id: 82592,
        avatar_url: "https://avatars0.githubusercontent.com/u/82592?v=4",
        gravatar_id: "",
        url: "https://api.github.com/users/square",
        html_url: "https://github.com/square",
        followers_url: "https://api.github.com/users/square/followers",
        following_url: "https://api.github.com/users/square/following{/other_user}",
        gists_url: "https://api.github.com/users/square/gists{/gist_id}",
        starred_url: "https://api.github.com/users/square/starred{/owner}{/repo}",
        subscriptions_url: "https://api.github.com/users/square/subscriptions",
        organizations_url: "https://api.github.com/users/square/orgs",
        repos_url: "https://api.github.com/users/square/repos",
        events_url: "https://api.github.com/users/square/events{/privacy}",
        received_events_url: "https://api.github.com/users/square/received_events",
        type: "Organization",
        site_admin: false
        },
        private: false,
        html_url: "https://github.com/square/dagger",
        description: "A fast dependency injector for Android and Java.",
        fork: false,
        url: "https://api.github.com/repos/square/dagger",
        forks_url: "https://api.github.com/repos/square/dagger/forks",
        keys_url: "https://api.github.com/repos/square/dagger/keys{/key_id}",
        collaborators_url: "https://api.github.com/repos/square/dagger/collaborators{/collaborator}",
        teams_url: "https://api.github.com/repos/square/dagger/teams",
        hooks_url: "https://api.github.com/repos/square/dagger/hooks",
        issue_events_url: "https://api.github.com/repos/square/dagger/issues/events{/number}",
        events_url: "https://api.github.com/repos/square/dagger/events",
        assignees_url: "https://api.github.com/repos/square/dagger/assignees{/user}",
        branches_url: "https://api.github.com/repos/square/dagger/branches{/branch}",
        tags_url: "https://api.github.com/repos/square/dagger/tags",
        blobs_url: "https://api.github.com/repos/square/dagger/git/blobs{/sha}",
        git_tags_url: "https://api.github.com/repos/square/dagger/git/tags{/sha}",
        git_refs_url: "https://api.github.com/repos/square/dagger/git/refs{/sha}",
        trees_url: "https://api.github.com/repos/square/dagger/git/trees{/sha}",
        statuses_url: "https://api.github.com/repos/square/dagger/statuses/{sha}",
        languages_url: "https://api.github.com/repos/square/dagger/languages",
        stargazers_url: "https://api.github.com/repos/square/dagger/stargazers",
        contributors_url: "https://api.github.com/repos/square/dagger/contributors",
        subscribers_url: "https://api.github.com/repos/square/dagger/subscribers",
        subscription_url: "https://api.github.com/repos/square/dagger/subscription",
        commits_url: "https://api.github.com/repos/square/dagger/commits{/sha}",
        git_commits_url: "https://api.github.com/repos/square/dagger/git/commits{/sha}",
        comments_url: "https://api.github.com/repos/square/dagger/comments{/number}",
        issue_comment_url: "https://api.github.com/repos/square/dagger/issues/comments{/number}",
        contents_url: "https://api.github.com/repos/square/dagger/contents/{+path}",
        compare_url: "https://api.github.com/repos/square/dagger/compare/{base}...{head}",
        merges_url: "https://api.github.com/repos/square/dagger/merges",
        archive_url: "https://api.github.com/repos/square/dagger/{archive_format}{/ref}",
        downloads_url: "https://api.github.com/repos/square/dagger/downloads",
        issues_url: "https://api.github.com/repos/square/dagger/issues{/number}",
        pulls_url: "https://api.github.com/repos/square/dagger/pulls{/number}",
        milestones_url: "https://api.github.com/repos/square/dagger/milestones{/number}",
        notifications_url: "https://api.github.com/repos/square/dagger/notifications{?since,all,participating}",
        labels_url: "https://api.github.com/repos/square/dagger/labels{/name}",
        releases_url: "https://api.github.com/repos/square/dagger/releases{/id}",
        deployments_url: "https://api.github.com/repos/square/dagger/deployments",
        created_at: "2012-08-02T23:25:32Z",
        updated_at: "2018-01-02T10:28:25Z",
        pushed_at: "2017-11-02T20:44:29Z",
        git_url: "git://github.com/square/dagger.git",
        ssh_url: "git@github.com:square/dagger.git",
        clone_url: "https://github.com/square/dagger.git",
        svn_url: "https://github.com/square/dagger",
        homepage: "http://square.github.io/dagger/",
        size: 7033,
        stargazers_count: 6590,
        watchers_count: 6590,
        language: "Java",
        has_issues: true,
        has_projects: false,
        has_downloads: true,
        has_wiki: false,
        has_pages: true,
        forks_count: 2187,
        mirror_url: null,
        archived: false,
        open_issues_count: 60,
        license: {
        key: "apache-2.0",
        name: "Apache License 2.0",
        spdx_id: "Apache-2.0",
        url: "https://api.github.com/licenses/apache-2.0"
        },
        forks: 2187,
        open_issues: 60,
        watchers: 6590,
        default_branch: "master",
        score: 144.57114
}
*/