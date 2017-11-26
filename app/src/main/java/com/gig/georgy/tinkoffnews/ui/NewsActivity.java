package com.gig.georgy.tinkoffnews.ui;

import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;

import com.gig.georgy.tinkoffnews.R;
import com.gig.georgy.tinkoffnews.app.BaseActivity;
import com.gig.georgy.tinkoffnews.common.adapters.RecyclerNewsAdapter;
import com.gig.georgy.tinkoffnews.common.enums.SnackBarType;
import com.gig.georgy.tinkoffnews.di.HasComponent;
import com.gig.georgy.tinkoffnews.di.components.DaggerMainComponent;
import com.gig.georgy.tinkoffnews.di.components.MainComponent;
import com.gig.georgy.tinkoffnews.di.components.TinkoffNewsAppComponent;
import com.gig.georgy.tinkoffnews.di.module.MainModule;
import com.gig.georgy.tinkoffnews.model.News;

import java.util.List;

import javax.inject.Inject;

public class NewsActivity extends BaseActivity implements HasComponent<MainComponent>, NewsView {

    @Inject
    public NewsPresenterImpl presenter;

    private MainComponent component;

    private RecyclerNewsAdapter recyclerNewsAdapter;
    private SwipeRefreshLayout swipeRefreshLayout;

    private ProgressBar progressBar;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        // toolbar
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        progressBar = findViewById(R.id.progressBar);

        final RecyclerView rvValues = (RecyclerView) findViewById(R.id.rvNews);

        rvValues.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        rvValues.setItemAnimator(new DefaultItemAnimator());

        recyclerNewsAdapter = new RecyclerNewsAdapter();

        rvValues.setLayoutManager(new LinearLayoutManager(this));
        rvValues.setAdapter(recyclerNewsAdapter);

        swipeRefreshLayout = findViewById(R.id.layoutSwipeRefresh);
        swipeRefreshLayout.setOnRefreshListener(() -> {
            hideProgress();
            presenter.refreshOrLoadNews();
        });

        presenter.refreshOrLoadNews();
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        toolbar.setTitle(getResources().getString(R.string.news_activity_title));
    }

    @Override
    protected void onResume() {
        presenter.onAttachView();

        super.onResume();
    }

    @Override
    protected void onPause() {
        presenter.onDetachView();

        super.onPause();
    }

    //=======--------- NewsView implement method START ---------=========

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void showMessage(int message, @SnackBarType int type) {
        showSnackBar(getWindow().getDecorView().getRootView(), message, type);
    }

    @Override
    public void updateDateInRealm(List<News> newsList) {
        recyclerNewsAdapter.update(newsList);
        swipeRefreshLayout.setRefreshing(false);
    }


    //=======--------- NewsView implement method END ---------=========

    // BaseActivity extended method =========
    @Override
    protected void setupComponent(TinkoffNewsAppComponent appComponent) {
        component = DaggerMainComponent.builder()
                .tinkoffNewsAppComponent(appComponent)
                .mainModule(new MainModule(this))
                .build();
        component.inject(this);
    }

    // HasComponent implement method =========
    @Override
    public MainComponent getComponent() {
        return component;
    }
}

