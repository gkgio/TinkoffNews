package com.gig.georgy.tinkoffnews.ui;

/**
 * Created by georgy on 25.11.2017.
 * GIG
 */

public interface NewsPresenter {
    void onAttachView();
    void onDetachView();
    void refreshOrLoadNews();
}
