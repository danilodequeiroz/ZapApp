package com.brutcode.seedapp.view;

import android.app.Application;

/**
 * Created by Danilo on 06/07/2016.
 */
public interface BaseDialogView{

    Application getApp();

    void longToast(int stringResource);

    void showDialog();

    void hideDialog();
}
