package com.gig.georgy.tinkoffnews.ui;

import com.gig.georgy.tinkoffnews.R;
import com.gig.georgy.tinkoffnews.common.enums.SnackBarType;
import com.gig.georgy.tinkoffnews.common.eventbus.Bus;
import com.gig.georgy.tinkoffnews.common.eventbus.events.HttpErrorEvent;
import com.gig.georgy.tinkoffnews.common.eventbus.events.ThrowableEvent;
import com.gig.georgy.tinkoffnews.common.eventbus.events.main.ResponseDataEvent;
import com.gig.georgy.tinkoffnews.model.News;
import com.gig.georgy.tinkoffnews.model.ResponseData;
import com.gig.georgy.tinkoffnews.network.NetworkService;

import java.net.HttpURLConnection;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.realm.Realm;
import retrofit2.Response;

/**
 * Created by georgy on 25.11.2017.
 * GIG
 */

public class NewsPresenterImpl implements NewsPresenter {

    @Inject
    public Bus bus;

    @Inject @Named("no_cached")
    public NetworkService networkService;

    private NewsView view;

    private CompositeDisposable disposables;

    @Inject
    public NewsPresenterImpl(NewsView view) {
        this.view = view;
    }

    @Override
    public void onAttachView() {
        disposables = new CompositeDisposable(subscribeToBus());
    }

    @Override
    public void onDetachView() {
        disposables.clear();
    }

    private Disposable subscribeToBus() {
        return bus.toObservable()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(event -> {
                    view.hideProgress();
                    if (event instanceof ResponseDataEvent) {
                        final ResponseData responseData = ((ResponseDataEvent) event).getResponseData();
                        prepareDataForAdapter(responseData.getNewsList());
                    }
                    if (event instanceof HttpErrorEvent) {
                        view.showMessage(R.string.toast_error, SnackBarType.ERROR);
                    } else if (event instanceof ThrowableEvent) {
                        view.showMessage(R.string.toast_error, SnackBarType.ERROR);
                    }
                });
    }

    @Override
    public void refreshOrLoadNews() {
        view.showProgress();

        Observable<Response<ResponseData>> responseObservable =
                networkService.getNews();
        responseObservable.subscribe(response -> {
            final int responseCode = response.code();
            switch (responseCode) {
                case HttpURLConnection.HTTP_OK:
                    bus.send(new ResponseDataEvent(response.body()));
                    break;
                default:
                    bus.send(new HttpErrorEvent(responseCode));
                    break;
            }
        }, throwable -> {
            throwable.printStackTrace();
            // в случае ошибки достаем данные из кэша
            Realm realm = null;
            try {
                realm = Realm.getDefaultInstance();
                realm.beginTransaction();

                ResponseData responseData = realm.where(ResponseData.class).findFirst();
                if (responseData == null) {
                    realm.cancelTransaction();
                    bus.send(new ThrowableEvent(throwable));
                } else {
                    realm.commitTransaction();
                    bus.send(new ResponseDataEvent(realm.copyFromRealm(responseData)));
                }
            } catch (Throwable e) {
                if (realm != null && realm.isInTransaction()) {
                    realm.cancelTransaction();
                }
                bus.send(new ThrowableEvent(throwable));
            } finally {
                if (realm != null) {
                    realm.close();
                }
            }

        });
    }

    private void prepareDataForAdapter(List newsList) {
        
    }
}
