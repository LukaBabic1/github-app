package com.undabot.babic.domain.usecase;

import com.undabot.babic.domain.model.CodeRepository;
import com.undabot.babic.domain.repository.CodeRepositoryRepository.SearchOrder;

import java.util.List;

import rx.Single;

public interface SearchRepositoriesUseCase {

    Single<List<CodeRepository>> execute(Request request);

    final class Request {

        public final String searchText;
        public final SearchOrder searchOrder;

        public Request(final String searchText, final SearchOrder searchOrder, final int page) {
            this.searchText = searchText;
            this.searchOrder = searchOrder;
        }
    }
}
