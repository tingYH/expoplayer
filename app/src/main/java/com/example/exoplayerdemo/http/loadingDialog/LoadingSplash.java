package com.example.exoplayerdemo.http.loadingDialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.ViewGroup;

import com.example.exoplayerdemo.R;


/**
 *customer ProgressDialog
 */
public class LoadingSplash extends Dialog {

    // 提示的文字
    private int mResid;

    public LoadingSplash(Context context) {
        super(context, R.style.Dialog2);
        setCanceledOnTouchOutside(false);
        setCancelable(true);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loading_splash);
        getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
    }

    public void showDialog(LoadingSplash mLoadingDialog) {
        if (!mLoadingDialog.isShowing()) mLoadingDialog.show();
    }

    public void dismissDialog(LoadingSplash mLoadingDialog) {
        mLoadingDialog.dismiss();
    }
}
