package com.undabot.babic.data.network.client;

import com.undabot.babic.domain.model.User;

import rx.Single;

public interface UserClient {

    Single<User> fetchUser(String id);
}
