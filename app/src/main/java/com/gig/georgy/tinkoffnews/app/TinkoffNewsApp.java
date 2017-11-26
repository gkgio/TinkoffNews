package com.gig.georgy.tinkoffnews.app;

import android.app.Application;
import android.content.Context;

import com.gig.georgy.tinkoffnews.di.components.DaggerTinkoffNewsAppComponent;
import com.gig.georgy.tinkoffnews.di.components.TinkoffNewsAppComponent;
import com.gig.georgy.tinkoffnews.di.module.TinkoffNewsAppModule;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by georgy on 25.11.2017.
 * GIG
 */

public class TinkoffNewsApp extends Application {

    private TinkoffNewsAppComponent tinkoffNewsAppComponent;

    public static TinkoffNewsApp get(Context context) {
        return (TinkoffNewsApp) context.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();

        buildObjectGraphAndInject();

        Realm.init(this);
        RealmConfiguration config = new RealmConfiguration.Builder()
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(config);
    }

    public void buildObjectGraphAndInject() {
        tinkoffNewsAppComponent = DaggerTinkoffNewsAppComponent.builder()
                . tinkoffNewsAppModule(new TinkoffNewsAppModule(this))
                .build();
    }

    public TinkoffNewsAppComponent getAppComponent() {
        return tinkoffNewsAppComponent;
    }
}