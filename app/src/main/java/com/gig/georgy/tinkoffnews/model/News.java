package com.gig.georgy.tinkoffnews.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by georgy on 25.11.2017.
 * GIG
 */

public class News extends RealmObject implements Serializable {
    /** id */
    @PrimaryKey
    private long id;

    private String name;

    private String text;

    @SerializedName("publicationDate")
    private PublicationDate publicationDate;


    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getText() {
        return text;
    }

    public PublicationDate getPublicationDate() {
        return publicationDate;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setPublicationDate(PublicationDate publicationDate) {
        this.publicationDate = publicationDate;
    }
}
