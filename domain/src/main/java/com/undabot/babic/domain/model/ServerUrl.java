package com.undabot.babic.domain.model;

public final class ServerUrl {

    public final String value;

    public ServerUrl(final String value) {
        this.value = value;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        final ServerUrl serverUrl = (ServerUrl) o;

        return value != null ? value.equals(serverUrl.value) : serverUrl.value == null;
    }

    @Override
    public int hashCode() {
        return value != null ? value.hashCode() : 0;
    }
}
