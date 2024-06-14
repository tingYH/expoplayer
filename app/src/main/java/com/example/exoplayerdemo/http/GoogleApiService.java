package com.example.exoplayerdemo.http;

import com.example.exoplayerdemo.bean.RespData;

import retrofit2.http.GET;
import rx.Observable;

public interface GoogleApiService {


    @GET(URLConstant.exam)
    Observable<RespData> examData();


}
