package com.example.exoplayerdemo.http.rxandroid;


import android.content.Context;


import com.example.exoplayerdemo.http.loadingDialog.LoadingDialog;

import rx.subscriptions.CompositeSubscription;


public abstract class RxSubscribe<T> extends RxBaseSubscribe<T> {

    private CompositeSubscription mCompositeSubscription;

    private LoadingDialog mLoadingDialog;

    private Context mContext;

    private boolean isShow = true;



    private boolean showDialog() {
        return isShow;
    }

    protected RxSubscribe(Context context, CompositeSubscription compositeSubscription) {
        this.mContext = context;
        this.mCompositeSubscription = compositeSubscription;
    }

    /**
     * @param context               上下文
     * @param compositeSubscription 请求控制
     * @param showDialog            是否显示加载框
     */
    protected RxSubscribe(Context context, CompositeSubscription compositeSubscription, boolean showDialog) {
        //是否开启动画
        this.isShow = showDialog;
        this.mContext = context;
        this.mCompositeSubscription = compositeSubscription;
    }

    @Override
    public void onStart() {
        super.onStart();
        if (showDialog()) {
            showLoadingDialog();
        }
    }

    @Override
    public void onNext(final T t) {
//        super.onNext(t);
        if (mCompositeSubscription != null)
            if (!mCompositeSubscription.isUnsubscribed())
                _onNext(t);
    }

    @Override
    public void onCompleted() {
        super.onCompleted();
        if (showDialog())
            hideLoadingDialog();
    }

    @Override
    public void onError(Throwable e) {
        if (showDialog())
            hideLoadingDialog();
        super.onError(e);
    }

    private void showLoadingDialog() {
        if (mContext != null) {
            if (mLoadingDialog == null) {
                mLoadingDialog = new LoadingDialog(mContext);
            }
            try {
                if (mLoadingDialog.isShowing() || mLoadingDialog != null)
                    mLoadingDialog.dismiss();
                mLoadingDialog.show();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    private void hideLoadingDialog() {
        if (mContext != null && mLoadingDialog != null) {
            try {
                mLoadingDialog.dismiss();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    protected abstract void _onNext(T t);

    protected abstract void _onError(String message);
}

