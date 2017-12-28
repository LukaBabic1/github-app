package com.undabot.babic.domain.model;

public final class CodeRepository {

    public final int id;

    public final String name;
    public final String fullName;

    public final User owner;

    public final String description;
    public final String repositoryUrl;
    public final String homepageUrl;
    public final String language;

    public final long createdAt;
    public final long updatedAt;
    public final long pushedAt;

    public final int stargazersCount;
    public final int watchersCount;
    public final int forksCount;
    public final int openIssuesCount;
    public final float score;

    public final boolean isPrivate;
    public final boolean hasIssues;
    public final boolean hasProjects;
    public final boolean hasWiki;

    public CodeRepository(final int id, final String name, final String fullName, final User owner, final String description, final String repositoryUrl, final String homepageUrl,
                          final String language, final long createdAt, final long updatedAt, final long pushedAt, final int stargazersCount, final int watchersCount,
                          final int forksCount, final int openIssuesCount, final float score, final boolean isPrivate, final boolean hasIssues, final boolean hasProjects,
                          final boolean hasWiki) {
        this.id = id;
        this.name = name;
        this.fullName = fullName;
        this.owner = owner;
        this.description = description;
        this.repositoryUrl = repositoryUrl;
        this.homepageUrl = homepageUrl;
        this.language = language;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.pushedAt = pushedAt;
        this.stargazersCount = stargazersCount;
        this.watchersCount = watchersCount;
        this.forksCount = forksCount;
        this.openIssuesCount = openIssuesCount;
        this.score = score;
        this.isPrivate = isPrivate;
        this.hasIssues = hasIssues;
        this.hasProjects = hasProjects;
        this.hasWiki = hasWiki;
    }
}

