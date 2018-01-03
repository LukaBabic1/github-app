package com.undabot.babic.app.ui.search;

import java.util.List;

public final class RepositorySearchScreenViewModel {

    public final List<RepositoryViewModel> repositoryViewModels;
    public final boolean canLoadMore;

    public RepositorySearchScreenViewModel(final List<RepositoryViewModel> repositoryViewModels, final boolean canLoadMore) {
        this.repositoryViewModels = repositoryViewModels;
        this.canLoadMore = canLoadMore;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        final RepositorySearchScreenViewModel that = (RepositorySearchScreenViewModel) o;

        if (canLoadMore != that.canLoadMore) {
            return false;
        }
        return repositoryViewModels != null ? repositoryViewModels.equals(that.repositoryViewModels) : that.repositoryViewModels == null;
    }

    @Override
    public int hashCode() {
        int result = repositoryViewModels != null ? repositoryViewModels.hashCode() : 0;
        result = 31 * result + (canLoadMore ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return "RepositorySearchScreenViewModel{" +
                "codeRepositoryViewModels=" + repositoryViewModels +
                ", canLoadMore=" + canLoadMore +
                '}';
    }
}
