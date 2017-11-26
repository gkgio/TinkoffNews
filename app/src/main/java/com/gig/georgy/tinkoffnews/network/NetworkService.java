package com.gig.georgy.tinkoffnews.network;

import com.gig.georgy.tinkoffnews.model.ResponseData;

import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.GET;

/**
 * Created by georgy on 25.11.2017.
 * GIG
 */

public interface NetworkService {
    @GET("/v1/news")
    Observable<Response<ResponseData>> getNews();
}
