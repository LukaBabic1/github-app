package com.undabot.babic.app.ui.search;

import java.util.List;

public final class RepositorySearchScreenViewModel {

    public final List<CodeRepositoryViewModel> codeRepositoryViewModels;
    public final boolean canLoadMore;

    public RepositorySearchScreenViewModel(final List<CodeRepositoryViewModel> codeRepositoryViewModels, final boolean canLoadMore) {
        this.codeRepositoryViewModels = codeRepositoryViewModels;
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
        return codeRepositoryViewModels != null ? codeRepositoryViewModels.equals(that.codeRepositoryViewModels) : that.codeRepositoryViewModels == null;
    }

    @Override
    public int hashCode() {
        int result = codeRepositoryViewModels != null ? codeRepositoryViewModels.hashCode() : 0;
        result = 31 * result + (canLoadMore ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return "RepositorySearchScreenViewModel{" +
                "codeRepositoryViewModels=" + codeRepositoryViewModels +
                ", canLoadMore=" + canLoadMore +
                '}';
    }
}
