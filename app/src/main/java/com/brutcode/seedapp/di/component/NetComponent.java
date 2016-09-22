package com.brutcode.seedapp.di.component;

import android.content.SharedPreferences;

import com.brutcode.seedapp.di.module.AppModule;
import com.brutcode.seedapp.di.module.NetModule;

import javax.inject.Singleton;

import dagger.Component;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

/**
 * Created by Danilo on 04/07/2016.
 */
@Singleton
@Component(modules={AppModule.class, NetModule.class})
public interface NetComponent {
    Retrofit retrofit();
    OkHttpClient okHttpClient();
    SharedPreferences sharedPreferences();
    // void inject(MyFragment fragment);
    // void inject(MyService service);
}
