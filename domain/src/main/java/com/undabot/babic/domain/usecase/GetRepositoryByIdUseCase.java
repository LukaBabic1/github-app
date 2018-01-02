package com.undabot.babic.domain.usecase;

import com.undabot.babic.domain.model.CodeRepository;

import rx.Observable;

public interface GetRepositoryByIdUseCase {

    Observable<CodeRepository> execute(int id);
}
