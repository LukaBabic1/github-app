package com.undabot.babic.app.ui.repositorydetail;

import com.undabot.babic.app.ui.search.RepositoryOwnerViewModel;

public final class RepositoryDetailViewModel {

    public final int id;
    public final String name;
    public final String fullName;
    public final RepositoryOwnerViewModel repositoryOwnerViewModel;
    public final int stargazersCount;
    public final int watchersCount;
    public final int forksCount;
    public final int openIssuesCount;
    public final float score;
    public final String language;
    public final String createdAt;
    public final String updatedAt;

    public RepositoryDetailViewModel(final int id, final String name, final String fullName, final RepositoryOwnerViewModel repositoryOwnerViewModel, final int stargazersCount,
                                     final int watchersCount, final int forksCount, final int openIssuesCount, final float score, final String language, final String createdAt,
                                     final String updatedAt) {
        this.id = id;
        this.name = name;
        this.fullName = fullName;
        this.repositoryOwnerViewModel = repositoryOwnerViewModel;
        this.stargazersCount = stargazersCount;
        this.watchersCount = watchersCount;
        this.forksCount = forksCount;
        this.openIssuesCount = openIssuesCount;
        this.score = score;
        this.language = language;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        final RepositoryDetailViewModel that = (RepositoryDetailViewModel) o;

        if (id != that.id) {
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
        if (name != null ? !name.equals(that.name) : that.name != null) {
            return false;
        }
        if (fullName != null ? !fullName.equals(that.fullName) : that.fullName != null) {
            return false;
        }
        if (repositoryOwnerViewModel != null ? !repositoryOwnerViewModel.equals(that.repositoryOwnerViewModel) : that.repositoryOwnerViewModel != null) {
            return false;
        }
        if (language != null ? !language.equals(that.language) : that.language != null) {
            return false;
        }
        if (createdAt != null ? !createdAt.equals(that.createdAt) : that.createdAt != null) {
            return false;
        }
        return updatedAt != null ? updatedAt.equals(that.updatedAt) : that.updatedAt == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (fullName != null ? fullName.hashCode() : 0);
        result = 31 * result + (repositoryOwnerViewModel != null ? repositoryOwnerViewModel.hashCode() : 0);
        result = 31 * result + stargazersCount;
        result = 31 * result + watchersCount;
        result = 31 * result + forksCount;
        result = 31 * result + openIssuesCount;
        result = 31 * result + (score != +0.0f ? Float.floatToIntBits(score) : 0);
        result = 31 * result + (language != null ? language.hashCode() : 0);
        result = 31 * result + (createdAt != null ? createdAt.hashCode() : 0);
        result = 31 * result + (updatedAt != null ? updatedAt.hashCode() : 0);
        return result;
    }
}
