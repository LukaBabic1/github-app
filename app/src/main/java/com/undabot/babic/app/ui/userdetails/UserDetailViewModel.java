package com.undabot.babic.app.ui.userdetails;

public final class UserDetailViewModel {

    public final int id;

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

    public final String createdAt;
    public final String updatedAt;

    public final int privateGists;
    public final int publicRepos;
    public final int publicGists;
    public final int totalPrivateRepos;
    public final int ownedPrivateRepos;

    public UserDetailViewModel(final int id, final String username, final String avatarUrl, final String siteHtmlUrl, final String name, final String type,
                               final String companyName, final String location, final String email, final boolean siteAdmin, final boolean hireable, final int followers,
                               final int following, final String createdAt, final String updatedAt, final int privateGists, final int publicRepos, final int publicGists,
                               final int totalPrivateRepos, final int ownedPrivateRepos) {
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

        final UserDetailViewModel that = (UserDetailViewModel) o;

        if (id != that.id) {
            return false;
        }
        if (siteAdmin != that.siteAdmin) {
            return false;
        }
        if (hireable != that.hireable) {
            return false;
        }
        if (followers != that.followers) {
            return false;
        }
        if (following != that.following) {
            return false;
        }
        if (privateGists != that.privateGists) {
            return false;
        }
        if (publicRepos != that.publicRepos) {
            return false;
        }
        if (publicGists != that.publicGists) {
            return false;
        }
        if (totalPrivateRepos != that.totalPrivateRepos) {
            return false;
        }
        if (ownedPrivateRepos != that.ownedPrivateRepos) {
            return false;
        }
        if (username != null ? !username.equals(that.username) : that.username != null) {
            return false;
        }
        if (avatarUrl != null ? !avatarUrl.equals(that.avatarUrl) : that.avatarUrl != null) {
            return false;
        }
        if (siteHtmlUrl != null ? !siteHtmlUrl.equals(that.siteHtmlUrl) : that.siteHtmlUrl != null) {
            return false;
        }
        if (name != null ? !name.equals(that.name) : that.name != null) {
            return false;
        }
        if (type != null ? !type.equals(that.type) : that.type != null) {
            return false;
        }
        if (companyName != null ? !companyName.equals(that.companyName) : that.companyName != null) {
            return false;
        }
        if (location != null ? !location.equals(that.location) : that.location != null) {
            return false;
        }
        if (email != null ? !email.equals(that.email) : that.email != null) {
            return false;
        }
        if (createdAt != null ? !createdAt.equals(that.createdAt) : that.createdAt != null) {
            return false;
        }
        return updatedAt != null ? updatedAt.equals(that.updatedAt) : that.updatedAt == null;
    }

    @Override
    public int hashCode() {
        int result = id;
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
        result = 31 * result + (createdAt != null ? createdAt.hashCode() : 0);
        result = 31 * result + (updatedAt != null ? updatedAt.hashCode() : 0);
        result = 31 * result + privateGists;
        result = 31 * result + publicRepos;
        result = 31 * result + publicGists;
        result = 31 * result + totalPrivateRepos;
        result = 31 * result + ownedPrivateRepos;
        return result;
    }

    @Override
    public String toString() {
        return "UserDetailViewModel{" +
                "id=" + id +
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
                ", createdAt='" + createdAt + '\'' +
                ", updatedAt='" + updatedAt + '\'' +
                ", privateGists=" + privateGists +
                ", publicRepos=" + publicRepos +
                ", publicGists=" + publicGists +
                ", totalPrivateRepos=" + totalPrivateRepos +
                ", ownedPrivateRepos=" + ownedPrivateRepos +
                '}';
    }
}
