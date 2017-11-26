package com.gig.georgy.tinkoffnews.ui;

import com.gig.georgy.tinkoffnews.common.enums.SnackBarType;
import com.gig.georgy.tinkoffnews.model.News;

import java.util.List;

/**
 * Created by georgy on 25.11.2017.
 * GIG
 */

public interface NewsView {
    void showProgress();
    void hideProgress();
    void showMessage(int message, @SnackBarType int type);
    void updateDateInRealm(List<News> newsList);
}
