package com.example.exoplayerdemo.http;


import com.example.exoplayerdemo.bean.RespData;
import com.example.exoplayerdemo.http.retrofit.RetrofitUtil;

import rx.Observable;

public class GoogleApiWrapper extends RetrofitUtil {

    public static GoogleApiWrapper getInstance() {
        return new GoogleApiWrapper();
    }


    public Observable<RespData> sendMSG() {
        return getService().examData();
    }

}
