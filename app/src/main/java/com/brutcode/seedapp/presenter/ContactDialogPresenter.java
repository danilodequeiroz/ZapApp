package com.brutcode.seedapp.presenter;

import com.brutcode.seedapp.MyApp;
import com.brutcode.seedapp.R;
import com.brutcode.seedapp.di.module.ZapTestApiModule;
import com.brutcode.seedapp.model.Contact;
import com.brutcode.seedapp.response.ContactResponse;
import com.brutcode.seedapp.view.ContactDialogView;

import javax.inject.Inject;

import retrofit2.Response;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Danilo on 06/07/2016.
 */
public class ContactDialogPresenter implements Presenter<ContactDialogView> {

    private static final String TAG = ContactDialogPresenter.class.getSimpleName();

    @Inject
    ZapTestApiModule.ZapTestInterface mZapTestInterface;

    private ContactDialogView mView;


    public void postContact(Contact contact) {
        mZapTestInterface.postContact(contact)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Response<ContactResponse>>() {
                    @Override
                    public void onCompleted() {}

                    @Override
                    public void onError(Throwable e) {
                        mView.longToast(R.string.service_failure);
                    }

                    @Override
                    public void onNext(Response<ContactResponse> responseObj) {
                        if(responseObj.body().getMsg().equals(ContactResponse.ContactEnum.OK))
                            mView.longToast(R.string.str_delivered_message);
                        else
                            mView.longToast(R.string.service_failure);
                    }
                });
    }

    @Override
    public void setView(ContactDialogView view) {
        ((MyApp) view.getApp()).getZapTestComponent().inject(this);
        mView = view;
    }
}
