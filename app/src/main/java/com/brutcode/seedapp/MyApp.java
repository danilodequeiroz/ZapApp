package com.brutcode.seedapp;

import android.app.Application;

import com.brutcode.seedapp.di.component.DaggerNetComponent;
import com.brutcode.seedapp.di.component.DaggerZapTestApiComponent;
import com.brutcode.seedapp.di.component.NetComponent;
import com.brutcode.seedapp.di.component.ZapTestApiComponent;
import com.brutcode.seedapp.di.module.AppModule;
import com.brutcode.seedapp.di.module.NetModule;
import com.brutcode.seedapp.di.module.ZapTestApiModule;
import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * Created by Danilo on 24/06/2016.
 */
public class MyApp extends Application {

    private NetComponent mNetComponent;
    private ZapTestApiComponent mZapTestApiComponent;

    private AppModule mAppModule;

    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
        mAppModule = new AppModule(this);
        // Dagger%COMPONENT_NAME%
        mNetComponent = DaggerNetComponent.builder()
                // list of modules that are part of this component need to be created here too
                .appModule(mAppModule) // This also corresponds to the name of your module: %component_name%Module
                .netModule(new NetModule(Constants.ZAPTESTAPIURI))
                .build();
        mZapTestApiComponent = DaggerZapTestApiComponent.builder()
                .netComponent(mNetComponent)
                .zapTestApiModule(new ZapTestApiModule())
                .build();

        // If a Dagger 2 component does not have any constructor arguments for any of its modules,
        // then we can use .create() as a shortcut instead:
        //  mAppComponent = com.codepath.dagger.components.DaggerNetComponent.create();
    }

    public NetComponent getNetComponent() {
        return mNetComponent;
    }

    public ZapTestApiComponent getZapTestComponent() {
        return mZapTestApiComponent;
    }


}
