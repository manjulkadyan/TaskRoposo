package com.manjul.roposo;

/**
 * Created by rnf-35 on 10/5/16.
 */
public class DataRoposo {
    private String about;
    private String id;
    private String username;
    private String following;
    private String followers;
    private String image;
    private String url;

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

    private String handle;
    private String is_following;
    private String createdOn;
    private  String description,verb,db,si,type,title,like_flag,likes_count,comment_count;

    public String getDescription() {
        return description;
    }


    public String getVerb() {
        return verb;
    }


    public String getDb() {
        return db;
    }


    public String getSi() {
        return si;
    }


    public String getType() {
        return type;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLike_flag() {
        return like_flag;
    }


    public String getLikes_count() {
        return likes_count;
    }


    public String getComment_count() {
        return comment_count;
    }


    public DataRoposo(String about, String genre,String username, String following,String followers,String image,String url,String handle,
                      String is_following,String createdOn,String description,String verb,String db,String si,String type,String title,String like_flag,String likes_count,String comment_count) {
        if(about!=null||about!=""){
        this.about = about;}
        else this.about = "null";
        this.id = genre;
        this.username = username;
        this.followers = followers;
        this.following= following;
        this.image = image;
        this.url= url;
        this.handle= handle;
        this.is_following= is_following;
        this.createdOn= createdOn;
        this.description= description;
        this.verb= verb;
        this.db= db;
        this.si= si;
        this.type= type;
        this.title= title;
        this.like_flag= like_flag;
        this.likes_count= likes_count;
        this.comment_count= comment_count;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String name) {
        this.about = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}