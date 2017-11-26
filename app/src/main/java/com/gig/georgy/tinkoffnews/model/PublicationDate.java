package com.gig.georgy.tinkoffnews.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by georgy on 26.11.2017.
 * GIG
 */

public class PublicationDate extends RealmObject implements Serializable {
    /** id */
    @PrimaryKey
    private long id;

    @SerializedName("milliseconds")
    private long time;


    public long getId() {
        return id;
    }

    public long getTime() {
        return time;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setTime(long time) {
        this.time = time;
    }
}
