package com.brutcode.seedapp.presenter;

import com.brutcode.seedapp.ui.result.DetailScrollingActivity;
import com.brutcode.seedapp.MyApp;
import com.brutcode.seedapp.R;
import com.brutcode.seedapp.di.module.ZapTestApiModule;
import com.brutcode.seedapp.model.Realty;
import com.brutcode.seedapp.model.RealtyContainer;
import com.brutcode.seedapp.view.DetailView;
import com.facebook.common.references.CloseableReference;
import com.facebook.imagepipeline.image.CloseableImage;

import javax.inject.Inject;

import retrofit2.Response;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Danilo on 06/07/2016.
 */
public class DetailPresenter implements Presenter<DetailView> {

    private static final String TAG = DetailPresenter.class.getSimpleName();

    @Inject
    ZapTestApiModule.ZapTestInterface mZapTestInterface;

    private DetailView mView;

    private Subscription mGetContentSubscription;

    private CloseableReference<CloseableImage> mRef;

    private Realty mRealty;

    @Override
    public void setView(DetailView view) {
        ((MyApp) view.getApp()).getZapTestComponent().inject(this);
        mView = view;
    }

    public void requestRealtyByCode(int cod) {
        mView.showProgress();
        mGetContentSubscription = mZapTestInterface
                .getDetail(cod)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Response<RealtyContainer>>() {
                    @Override
                    public void onCompleted() {
                        mView.presentContent(mRealty);
                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.longToast(R.string.service_failure);
                        mView.hideProgress();
                        ((DetailScrollingActivity)mView).finish();
                    }

                    @Override
                    public void onNext(Response<RealtyContainer> realtyResponse) {
                        mRealty = realtyResponse.body().getmRealty();
                    }
                });
    }

}
