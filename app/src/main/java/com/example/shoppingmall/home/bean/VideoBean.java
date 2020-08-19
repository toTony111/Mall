package com.example.shoppingmall.home.bean;

import com.alibaba.fastjson.JSONObject;

public class VideoBean extends JSONObject {

    /**
     * id : 554
     * title : 瘦脸针💉全程记录！避雷干货分享👋
     * cover : https://admin-miaomeimei-1301812909.cos.ap-guangzhou.myqcloud.com/video/yimei/20200811174101_24_41.jpeg
     * video : https://admin-miaomeimei-1301812909.cos.ap-guangzhou.myqcloud.com/video/yimei/20200811174101_24_41.mp4
     * likes : 2669
     * shares : 306
     * comments : 0
     * add_time : 0
     * uid : 654
     * is_hot : 0
     * position : null
     * user_nickname : 情系@半生
     * user_avatarurl : https://admin-miaomeimei-1301812909.cos.ap-guangzhou.myqcloud.com/admin/userNickName/2020_08_03_9ff37d3d786a369f16bc0b9aadbb06af.jpg
     */

    private int id;
    private String title;
    private String cover;
    private String video;
    private int likes;
    private int shares;
    private int comments;
    private int add_time;
    private int uid;
    private int is_hot;
    private Object position;
    private String user_nickname;
    private String user_avatarurl;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getShares() {
        return shares;
    }

    public void setShares(int shares) {
        this.shares = shares;
    }

    public int getComments() {
        return comments;
    }

    public void setComments(int comments) {
        this.comments = comments;
    }

    public int getAdd_time() {
        return add_time;
    }

    public void setAdd_time(int add_time) {
        this.add_time = add_time;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getIs_hot() {
        return is_hot;
    }

    public void setIs_hot(int is_hot) {
        this.is_hot = is_hot;
    }

    public Object getPosition() {
        return position;
    }

    public void setPosition(Object position) {
        this.position = position;
    }

    public String getUser_nickname() {
        return user_nickname;
    }

    public void setUser_nickname(String user_nickname) {
        this.user_nickname = user_nickname;
    }

    public String getUser_avatarurl() {
        return user_avatarurl;
    }

    public void setUser_avatarurl(String user_avatarurl) {
        this.user_avatarurl = user_avatarurl;
    }
}
