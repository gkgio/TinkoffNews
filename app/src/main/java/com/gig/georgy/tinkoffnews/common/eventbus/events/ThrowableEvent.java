package com.gig.georgy.tinkoffnews.common.eventbus.events;

/**
 * Created by georgy on 26.11.2017.
 * GIG
 */

public class ThrowableEvent {

    private final Throwable trowable;

    public ThrowableEvent(Throwable trowable) {
        this.trowable = trowable;
    }

    public Throwable getTrowable() {
        return trowable;
    }
}
