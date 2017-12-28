package com.undabot.babic.app.ui.search;

public final class CodeRepositoryViewModel {

    public final int id;
    public final String name;
    public final String fullName;
    public final RepositoryOwnerViewModel repositoryOwnerViewModel;
    public final int stargazersCount;
    public final int watchersCount;
    public final int forksCount;
    public final int openIssuesCount;
    public final float score;

    public CodeRepositoryViewModel(final int id, final String name, final String fullName, final RepositoryOwnerViewModel repositoryOwnerViewModel, final int stargazersCount,
                                   final int watchersCount, final int forksCount, final int openIssuesCount, final float score) {
        this.id = id;
        this.name = name;
        this.fullName = fullName;
        this.repositoryOwnerViewModel = repositoryOwnerViewModel;
        this.stargazersCount = stargazersCount;
        this.watchersCount = watchersCount;
        this.forksCount = forksCount;
        this.openIssuesCount = openIssuesCount;
        this.score = score;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        final CodeRepositoryViewModel that = (CodeRepositoryViewModel) o;

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
        return repositoryOwnerViewModel != null ? repositoryOwnerViewModel.equals(that.repositoryOwnerViewModel) : that.repositoryOwnerViewModel == null;
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
        return result;
    }

    @Override
    public String toString() {
        return "CodeRepositoryViewModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", fullName='" + fullName + '\'' +
                ", userViewModel=" + repositoryOwnerViewModel +
                ", stargazersCount=" + stargazersCount +
                ", watchersCount=" + watchersCount +
                ", forksCount=" + forksCount +
                ", openIssuesCount=" + openIssuesCount +
                ", score=" + score +
                '}';
    }
}
