package com.undabot.babic.data.network.configuration;

import com.undabot.babic.domain.model.ServerUrl;

public final class UrlsImpl implements Urls {

    @Override
    public ServerUrl getServerUrl() {
        return new ServerUrl("");
    }
}
