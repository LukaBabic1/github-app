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

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        final CodeRepository that = (CodeRepository) o;

        if (id != that.id) {
            return false;
        }
        if (createdAt != that.createdAt) {
            return false;
        }
        if (updatedAt != that.updatedAt) {
            return false;
        }
        if (pushedAt != that.pushedAt) {
            return false;
        }
        if (stargazersCount != that.stargazersCount) {
            return false;
        }
        if (watchersCount != that.watchersCount) {
            return false;
        }
        if (forksCount != that.forksCount) {
            return false;
        }
        if (openIssuesCount != that.openIssuesCount) {
            return false;
        }
        if (Float.compare(that.score, score) != 0) {
            return false;
        }
        if (isPrivate != that.isPrivate) {
            return false;
        }
        if (hasIssues != that.hasIssues) {
            return false;
        }
        if (hasProjects != that.hasProjects) {
            return false;
        }
        if (hasWiki != that.hasWiki) {
            return false;
        }
        if (name != null ? !name.equals(that.name) : that.name != null) {
            return false;
        }
        if (fullName != null ? !fullName.equals(that.fullName) : that.fullName != null) {
            return false;
        }
        if (owner != null ? !owner.equals(that.owner) : that.owner != null) {
            return false;
        }
        if (description != null ? !description.equals(that.description) : that.description != null) {
            return false;
        }
        if (repositoryUrl != null ? !repositoryUrl.equals(that.repositoryUrl) : that.repositoryUrl != null) {
            return false;
        }
        if (homepageUrl != null ? !homepageUrl.equals(that.homepageUrl) : that.homepageUrl != null) {
            return false;
        }
        return language != null ? language.equals(that.language) : that.language == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (fullName != null ? fullName.hashCode() : 0);
        result = 31 * result + (owner != null ? owner.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (repositoryUrl != null ? repositoryUrl.hashCode() : 0);
        result = 31 * result + (homepageUrl != null ? homepageUrl.hashCode() : 0);
        result = 31 * result + (language != null ? language.hashCode() : 0);
        result = 31 * result + (int) (createdAt ^ (createdAt >>> 32));
        result = 31 * result + (int) (updatedAt ^ (updatedAt >>> 32));
        result = 31 * result + (int) (pushedAt ^ (pushedAt >>> 32));
        result = 31 * result + stargazersCount;
        result = 31 * result + watchersCount;
        result = 31 * result + forksCount;
        result = 31 * result + openIssuesCount;
        result = 31 * result + (score != +0.0f ? Float.floatToIntBits(score) : 0);
        result = 31 * result + (isPrivate ? 1 : 0);
        result = 31 * result + (hasIssues ? 1 : 0);
        result = 31 * result + (hasProjects ? 1 : 0);
        result = 31 * result + (hasWiki ? 1 : 0);
        return result;
    }
}

