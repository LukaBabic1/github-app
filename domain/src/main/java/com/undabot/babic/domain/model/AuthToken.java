package com.undabot.babic.domain.model;

public final class AuthToken {

    public static final AuthToken EMPTY = new AuthToken("");

    public final String value;

    public AuthToken(final String value) {
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

        final AuthToken authToken = (AuthToken) o;

        return value != null ? value.equals(authToken.value) : authToken.value == null;
    }

    @Override
    public int hashCode() {
        return value != null ? value.hashCode() : 0;
    }
}
