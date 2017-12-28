package com.undabot.babic.domain.usecase;

import com.undabot.babic.domain.model.CodeRepository;
import com.undabot.babic.domain.repository.CodeRepositoryRepository;

import java.util.List;

import rx.Single;

public interface SearchMoreRepositoriesUseCase {

    Single<List<CodeRepository>> execute(Request request);

    final class Request {

        public final String searchText;
        public final CodeRepositoryRepository.SearchOrder searchOrder;
        public final int page;

        public Request(final String searchText, final CodeRepositoryRepository.SearchOrder searchOrder, final int page, final int page1) {
            this.searchText = searchText;
            this.searchOrder = searchOrder;
            this.page = page1;
        }
    }
}
