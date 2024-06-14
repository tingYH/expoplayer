package com.example.exoplayerdemo.http.rxandroid;



import com.example.exoplayerdemo.http.APIException;
import com.example.exoplayerdemo.http.common.BaseResponse;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.util.concurrent.TimeoutException;

import retrofit2.adapter.rxjava.HttpException;
import rx.Subscriber;

public abstract class RxBaseSubscribe<T> extends Subscriber<T> {

    @Override
    public void onError(Throwable e) {
        String Message;
        e.printStackTrace();
        if (e instanceof APIException) {
            APIException exception = (APIException) e;
            if (exception.getCode() == BaseResponse.TOKEN_PAYMENT_ERROR_CODE) {
                _onError(exception.getCode() + "_" + exception.getMessage());
                Message = exception.getMessage();
            } else {
                _onError(exception.getMessage());
                Message = exception.getMessage();
            }
        } else if (e instanceof TimeoutException || e instanceof SocketTimeoutException ||
                e instanceof ConnectException || e instanceof HttpException) {
            _onError("加载超时,请稍后重试");
            Message = "加载超时,请稍后重试";
        } else {
            _onError("加载超时,请稍后重试");
            Message = "加载超时,请稍后重试";
        }

    }

    @Override
    public void onNext(T t) {
        _onNext(t);
    }

    @Override
    public void onCompleted() {

    }

    protected abstract void _onNext(T t);

    protected abstract void _onError(String message);
}

