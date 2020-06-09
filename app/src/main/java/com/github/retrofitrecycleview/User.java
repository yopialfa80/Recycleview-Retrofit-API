package com.github.retrofitrecycleview;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User {

    @SerializedName("name")
    @Expose
    String name;
    @SerializedName("stats")
    @Expose
    String stats;
    @SerializedName("picture")
    @Expose
    String picture;


    public User(String name, String stats, String picture){
        this.name = name;
        this.stats = stats;
        this.picture = picture;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStats() {
        return stats;
    }

    public void setStats(String stats) {
        this.stats = stats;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }
}
