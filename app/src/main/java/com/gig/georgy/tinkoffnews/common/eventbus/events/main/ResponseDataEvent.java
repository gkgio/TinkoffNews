package com.gig.georgy.tinkoffnews.common.eventbus.events.main;

import com.gig.georgy.tinkoffnews.model.ResponseData;

/**
 * Created by georgy on 26.11.2017.
 * GIG
 */

public class ResponseDataEvent {
    final ResponseData responseData;

    public ResponseDataEvent(ResponseData responseData) {
        this.responseData = responseData;
    }

    public ResponseData getResponseData() {
        return responseData;
    }
}
