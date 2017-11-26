package com.gig.georgy.tinkoffnews.di.module;

import com.gig.georgy.tinkoffnews.ui.NewsActivity;
import com.gig.georgy.tinkoffnews.ui.NewsPresenter;
import com.gig.georgy.tinkoffnews.ui.NewsPresenterImpl;
import com.gig.georgy.tinkoffnews.ui.NewsView;

import dagger.Module;
import dagger.Provides;

/**
 * Created by georgy on 25.11.2017.
 * GIG
 */

@Module
public class MainModule {
    private NewsActivity activity;

    public MainModule(NewsActivity activity) {
        this.activity = activity;
    }

    /** Provide NewsView */
    @Provides
    NewsView provideNewsView() {
        return activity;
    }

    /** Provide MainPresenterImpl */
    @Provides
    NewsPresenter provideNewsPresenterImpl(NewsView view) {
        return new NewsPresenterImpl(view);
    }
}
