package com.undabot.babic.data.network.configuration;

import android.content.res.Resources;

import com.annimon.stream.Optional;
import com.undabot.babic.data.R;
import com.undabot.babic.domain.model.ServerUrl;

public final class UrlsImpl implements Urls {

    private Optional<ServerUrl> serverUrl = Optional.empty();

    private final Resources resources;

    public UrlsImpl(final Resources resources) {
        this.resources = resources;
    }

    @Override
    public ServerUrl getServerUrl() {
        if (!serverUrl.isPresent()) {
            serverUrl = Optional.of(new ServerUrl(resources.getString(R.string.server_url)));
        }

        return serverUrl.get();
    }
}
