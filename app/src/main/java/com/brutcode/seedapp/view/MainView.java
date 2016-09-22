package com.brutcode.seedapp.view;

import com.brutcode.seedapp.model.Realty;

import java.util.List;

/**
 * Created by Danilo on 06/07/2016.
 */
public interface MainView extends BaseView{
    void presentContent(List<Realty> realties);
    void runOnUi(Runnable runnable);
}
