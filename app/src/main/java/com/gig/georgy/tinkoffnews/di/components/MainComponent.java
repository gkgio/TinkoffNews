package com.gig.georgy.tinkoffnews.di.components;

import com.gig.georgy.tinkoffnews.di.ActivityScope;
import com.gig.georgy.tinkoffnews.di.module.MainModule;
import com.gig.georgy.tinkoffnews.ui.NewsActivity;

import dagger.Component;

/**
 * Created by georgy on 25.11.2017.
 * GIG
 */

@ActivityScope
@Component(
        dependencies = TinkoffNewsAppComponent.class,
        modules = MainModule.class
)
public interface MainComponent {
    void inject(NewsActivity newsActivity);
}

