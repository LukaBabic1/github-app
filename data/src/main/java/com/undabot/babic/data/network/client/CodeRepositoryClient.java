package com.undabot.babic.data.network.client;

import com.undabot.babic.domain.model.CodeRepository;
import com.undabot.babic.domain.repository.CodeRepositoryRepository.SearchOrder;

import java.util.List;

import rx.Single;

public interface CodeRepositoryClient {

    /**
     * Searches for first page of repositories by specified search order.
     *
     * @param query Query text.
     * @param searchOrder Search order to be used.
     * @param perPage Number of items to be returned by page. Should not exceed 100.
     * @return
     */
    Single<List<CodeRepository>> searchRepositories(String query, SearchOrder searchOrder, int perPage);

    /**
     * Searches for consecutive pages of repositories by specified search order.
     *
     * @param query Query text.
     * @param searchOrder Search order to be used.
     * @param perPage Number of items to be returned by page. Should not exceed 100.
     * @param page 0-based pagination, meaning 0 will be first page.
     * @return
     */
    Single<List<CodeRepository>> searchRepositories(String query, SearchOrder searchOrder, int perPage, int page);
}
