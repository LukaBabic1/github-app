package com.undabot.babic.app.ui.search;

import com.undabot.babic.app.ui.search.RepositorySearchContract.Presenter.SearchOrderInt;

import java.util.List;

public final class RepositorySearchScreenViewModel {

    public final List<RepositoryViewModel> repositoryViewModels;
    public final int lastLoadedPage;
    public final String searchTerm;

    @SearchOrderInt
    public final int searchModeInt;

    public final boolean canLoadMore;

    public RepositorySearchScreenViewModel(final List<RepositoryViewModel> repositoryViewModels, final int lastLoadedPage, final String searchTerm,
                                           final int searchModeInt, final boolean canLoadMore) {
        this.repositoryViewModels = repositoryViewModels;
        this.lastLoadedPage = lastLoadedPage;
        this.searchTerm = searchTerm;
        this.searchModeInt = searchModeInt;
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

        if (lastLoadedPage != that.lastLoadedPage) {
            return false;
        }
        if (searchModeInt != that.searchModeInt) {
            return false;
        }
        if (canLoadMore != that.canLoadMore) {
            return false;
        }
        if (repositoryViewModels != null ? !repositoryViewModels.equals(that.repositoryViewModels) : that.repositoryViewModels != null) {
            return false;
        }
        return searchTerm != null ? searchTerm.equals(that.searchTerm) : that.searchTerm == null;
    }

    @Override
    public int hashCode() {
        int result = repositoryViewModels != null ? repositoryViewModels.hashCode() : 0;
        result = 31 * result + lastLoadedPage;
        result = 31 * result + (searchTerm != null ? searchTerm.hashCode() : 0);
        result = 31 * result + searchModeInt;
        result = 31 * result + (canLoadMore ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return "RepositorySearchScreenViewModel{" +
                "repositoryViewModels=" + repositoryViewModels +
                ", lastLoadedPage=" + lastLoadedPage +
                ", searchTerm='" + searchTerm + '\'' +
                ", searchModeInt=" + searchModeInt +
                ", canLoadMore=" + canLoadMore +
                '}';
    }
}
