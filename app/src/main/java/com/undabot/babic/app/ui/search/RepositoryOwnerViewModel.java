package com.undabot.babic.app.ui.search;

public final class RepositoryOwnerViewModel {

    public final int id;
    public final String username;
    public final String avatarUrl;
    public final String name;

    public RepositoryOwnerViewModel(final int id, final String username, final String avatarUrl, final String name) {
        this.id = id;
        this.username = username;
        this.avatarUrl = avatarUrl;
        this.name = name;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        final RepositoryOwnerViewModel that = (RepositoryOwnerViewModel) o;

        if (id != that.id) {
            return false;
        }
        if (username != null ? !username.equals(that.username) : that.username != null) {
            return false;
        }
        if (avatarUrl != null ? !avatarUrl.equals(that.avatarUrl) : that.avatarUrl != null) {
            return false;
        }
        return name != null ? name.equals(that.name) : that.name == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (avatarUrl != null ? avatarUrl.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "RepositoryOwnerViewModel{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", avatarUrl='" + avatarUrl + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
