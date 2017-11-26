package com.gig.georgy.tinkoffnews.di.components;

import android.app.Application;

import com.gig.georgy.tinkoffnews.app.TinkoffNewsApp;
import com.gig.georgy.tinkoffnews.common.eventbus.Bus;
import com.gig.georgy.tinkoffnews.di.module.TinkoffNewsAppModule;
import com.gig.georgy.tinkoffnews.network.NetworkService;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by georgy on 25.11.2017.
 * GIG
 */

@Singleton
@Component(modules = {TinkoffNewsAppModule.class})
public interface TinkoffNewsAppComponent {
    void inject(TinkoffNewsApp tinkoffApp);

    Application app();

    Bus eventBus();

    @Named("no_cached")
    NetworkService noCachedService();
}
