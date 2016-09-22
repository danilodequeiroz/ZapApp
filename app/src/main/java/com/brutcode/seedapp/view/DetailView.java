package com.brutcode.seedapp.view;

import com.brutcode.seedapp.model.Realty;

import java.util.List;

/**
 * Created by Danilo on 09/09/2016.
 */
public interface DetailView extends BaseView {
    void presentContent(Realty realty);
    void runOnUi(Runnable runnable);
}
