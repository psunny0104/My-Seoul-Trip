package com.myseoultrip.service;

import org.json.JSONException;

public interface ApiCallback<T> {
    void onError(Throwable t);

    void onSuccess(int code, T receivedData) throws JSONException;

    void onFailure(int code);
}
