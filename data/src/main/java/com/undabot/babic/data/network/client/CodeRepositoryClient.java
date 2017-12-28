package com.undabot.babic.data.network.client;

import com.undabot.babic.domain.model.CodeRepository;
import com.undabot.babic.domain.repository.CodeRepositoryRepository.SearchOrder;

import java.util.List;

import rx.Single;

public interface CodeRepositoryClient {

    Single<List<CodeRepository>> searchRepositories(String query, SearchOrder searchOrder, int perPage);

    Single<List<CodeRepository>> searchRepositories(String query, SearchOrder searchOrder, int perPage, int page);
}
