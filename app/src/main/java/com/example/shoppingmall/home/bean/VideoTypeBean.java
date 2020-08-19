package com.example.shoppingmall.home.bean;

import com.alibaba.fastjson.JSONObject;

public class VideoTypeBean extends JSONObject {
    /**
     * id : 0
     * name : 热门精选
     */

    private int id;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
