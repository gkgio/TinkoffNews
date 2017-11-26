package com.gig.georgy.tinkoffnews.common.eventbus;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;

/**
 * Created by georgy on 26.11.2017.
 * GIG
 */

public class Bus {
    private final PublishSubject<Object> bus;

    @Inject
    public Bus() {
        this.bus = PublishSubject.create();
    }

    public Observable<Object> toObservable() {
        return bus;
    }

    public void send(final Object event) {
        bus.onNext(event);
    }
}
