package com.undabot.babic.domain.model;

public final class User {

    public static final User EMPTY = new User("", "", "", "", "", "", "", "", "", false, false, 0, 0, 0L, 0L, 0, 0, 0, 0, 0);

    public final String id;
    public final String username;
    public final String avatarUrl;
    public final String siteHtmlUrl;
    public final String name;
    public final String type;
    public final String companyName;
    public final String location;
    public final String email;
    public final boolean siteAdmin;
    public final boolean hireable;
    public final int followers;
    public final int following;
    public final long createdAt;
    public final long updatedAt;
    public final int privateGists;
    public final int publicRepos;
    public final int publicGists;
    public final int totalPrivateRepos;
    public final int ownedPrivateRepos;

    public User(final String id, final String username, final String avatarUrl, final String siteHtmlUrl, final String name, final String type, final String companyName,
                final String location, final String email, final boolean siteAdmin, final boolean hireable, final int followers, final int following, final long createdAt,
                final long updatedAt, final int privateGists, final int publicRepos, final int publicGists, final int totalPrivateRepos, final int ownedPrivateRepos) {

        this.id = id;
        this.username = username;
        this.avatarUrl = avatarUrl;
        this.siteHtmlUrl = siteHtmlUrl;
        this.name = name;
        this.type = type;
        this.companyName = companyName;
        this.location = location;
        this.email = email;
        this.siteAdmin = siteAdmin;
        this.hireable = hireable;
        this.followers = followers;
        this.following = following;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.privateGists = privateGists;
        this.publicRepos = publicRepos;
        this.publicGists = publicGists;
        this.totalPrivateRepos = totalPrivateRepos;
        this.ownedPrivateRepos = ownedPrivateRepos;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        final User user = (User) o;

        if (siteAdmin != user.siteAdmin) {
            return false;
        }
        if (hireable != user.hireable) {
            return false;
        }
        if (followers != user.followers) {
            return false;
        }
        if (following != user.following) {
            return false;
        }
        if (createdAt != user.createdAt) {
            return false;
        }
        if (updatedAt != user.updatedAt) {
            return false;
        }
        if (privateGists != user.privateGists) {
            return false;
        }
        if (publicRepos != user.publicRepos) {
            return false;
        }
        if (publicGists != user.publicGists) {
            return false;
        }
        if (totalPrivateRepos != user.totalPrivateRepos) {
            return false;
        }
        if (ownedPrivateRepos != user.ownedPrivateRepos) {
            return false;
        }
        if (id != null ? !id.equals(user.id) : user.id != null) {
            return false;
        }
        if (username != null ? !username.equals(user.username) : user.username != null) {
            return false;
        }
        if (avatarUrl != null ? !avatarUrl.equals(user.avatarUrl) : user.avatarUrl != null) {
            return false;
        }
        if (siteHtmlUrl != null ? !siteHtmlUrl.equals(user.siteHtmlUrl) : user.siteHtmlUrl != null) {
            return false;
        }
        if (name != null ? !name.equals(user.name) : user.name != null) {
            return false;
        }
        if (type != null ? !type.equals(user.type) : user.type != null) {
            return false;
        }
        if (companyName != null ? !companyName.equals(user.companyName) : user.companyName != null) {
            return false;
        }
        if (location != null ? !location.equals(user.location) : user.location != null) {
            return false;
        }
        return email != null ? email.equals(user.email) : user.email == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (avatarUrl != null ? avatarUrl.hashCode() : 0);
        result = 31 * result + (siteHtmlUrl != null ? siteHtmlUrl.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (companyName != null ? companyName.hashCode() : 0);
        result = 31 * result + (location != null ? location.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (siteAdmin ? 1 : 0);
        result = 31 * result + (hireable ? 1 : 0);
        result = 31 * result + followers;
        result = 31 * result + following;
        result = 31 * result + (int) (createdAt ^ (createdAt >>> 32));
        result = 31 * result + (int) (updatedAt ^ (updatedAt >>> 32));
        result = 31 * result + privateGists;
        result = 31 * result + publicRepos;
        result = 31 * result + publicGists;
        result = 31 * result + totalPrivateRepos;
        result = 31 * result + ownedPrivateRepos;
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", avatarUrl='" + avatarUrl + '\'' +
                ", siteHtmlUrl='" + siteHtmlUrl + '\'' +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", companyName='" + companyName + '\'' +
                ", location='" + location + '\'' +
                ", email='" + email + '\'' +
                ", siteAdmin=" + siteAdmin +
                ", hireable=" + hireable +
                ", followers=" + followers +
                ", following=" + following +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", privateGists=" + privateGists +
                ", publicRepos=" + publicRepos +
                ", publicGists=" + publicGists +
                ", totalPrivateRepos=" + totalPrivateRepos +
                ", ownedPrivateRepos=" + ownedPrivateRepos +
                '}';
    }
}


