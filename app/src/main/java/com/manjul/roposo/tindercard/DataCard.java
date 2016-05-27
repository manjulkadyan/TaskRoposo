package com.manjul.roposo.tindercard;

/**
 * Created by nirav on 05/10/15.
 */
public class DataCard {
    private String about;
    private String id;
    private String username;
    private String following;
    private String followers;
    private String image;
    private String url;
    private String handle;
    private String is_following;
    private String createdOn;


    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFollowing() {
        return following;
    }

    public void setFollowing(String following) {
        this.following = following;
    }

    public String getFollowers() {
        return followers;
    }

    public void setFollowers(String followers) {
        this.followers = followers;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getHandle() {
        return handle;
    }

    public void setHandle(String handle) {
        this.handle = handle;
    }

    public String getIs_following() {
        return is_following;
    }

    public void setIs_following(String is_following) {
        this.is_following = is_following;
    }

    public String getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(String createdOn) {
        this.createdOn = createdOn;
    }

    public DataCard(String about, String genre,String username, String following,String followers,String image,String url,String handle,
                      String is_following,String createdOn) {
        if (about != null || about != "") {
            this.about = about;
        } else this.about = "null";
        this.id = genre;
        this.username = username;
        this.followers = followers;
        this.following = following;
        this.image = image;

        this.url = url;
        this.handle = handle;
        this.is_following = is_following;
        this.createdOn = createdOn;
    }
}
