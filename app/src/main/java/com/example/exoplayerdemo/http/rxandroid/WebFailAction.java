package com.example.exoplayerdemo.http.rxandroid;


import android.util.Log;
import rx.functions.Action1;

public class WebFailAction implements Action1<Throwable> {
    @Override
    public void call(Throwable throwable) {
        String getMessage = "";
        if (throwable != null) {
            Log.e("WebFailAction", "SystemError", throwable);
            if (throwable.toString().contains("No address associated with hostname"))
                getMessage = "Connection Issue, please check your Internet status.";
            else if (throwable.toString().contains("timeout"))
                getMessage = "timeout";
            else if (throwable.toString().contains("404"))
                getMessage = "404 - Cannot Find the Page";
            else if (throwable.toString().contains("502"))
                getMessage = "502 - Bad Gateway";
            else if (throwable.toString().contains("405"))
                getMessage = "405 - Overtime Loading";
            else if (throwable.toString().contains("500"))
                getMessage = "500 - Server Error";

            Log.e("WebFail", getMessage);
        } else {
            Log.e("WebFail", "Connection Issue, please check your Internet status.");
        }
    }


}
