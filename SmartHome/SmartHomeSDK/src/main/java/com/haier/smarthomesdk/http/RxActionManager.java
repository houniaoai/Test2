package com.haier.smarthomesdk.http;

import rx.Subscription;

public interface RxActionManager<T> {

    void add(T tag, Subscription subscription);

    void cancel(T tag);

    void cancelAll();
}