package com.gig.georgy.tinkoffnews.common;

import io.realm.RealmObject;

/**
 * Created by georgy on 25.11.2017.
 * GIG
 */

public class LongWrapper extends RealmObject {

    private long value;

    public LongWrapper(long value) {
        this.value = value;
    }

    public LongWrapper() {

    }

    public long getValue() {
        return value;
    }
}
