package com.gig.georgy.tinkoffnews.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by georgy on 26.11.2017.
 * GIG
 */

public class ResponseData extends RealmObject implements Serializable {
    /** id */
    @PrimaryKey
    private long id;

    @SerializedName("payload")
    private RealmList<News> newsList;

    public void setId(long id) {
        this.id = id;
    }

    public void setNewsList(RealmList<News> newsList) {
        this.newsList = newsList;
    }

    public RealmList<News> getNewsList() {
        return newsList;
    }

    public long getId() {

        return id;
    }
}
