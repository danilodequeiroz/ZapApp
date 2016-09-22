package com.brutcode.seedapp.view;

import android.app.Application;
import android.content.Context;
import android.content.res.Resources;

/**
 * Created by Danilo on 06/07/2016.
 */
public interface BaseView {
    Application getApp();

    Context getViewContext();

    Resources getAppResources();

    void longToast(int stringResource);

    void shortToast(int stringResource);

    void showProgress();

    void hideProgress();
}
