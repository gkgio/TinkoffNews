package com.gig.georgy.tinkoffnews.common.eventbus.events;

/**
 * Created by georgy on 26.11.2017.
 * GIG
 */

public class HttpErrorEvent {

    private final int code;

    public HttpErrorEvent(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
