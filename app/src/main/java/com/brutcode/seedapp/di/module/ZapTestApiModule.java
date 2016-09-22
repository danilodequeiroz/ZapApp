package com.brutcode.seedapp.di.module;

import com.brutcode.seedapp.di.scope.UserScope;
import com.brutcode.seedapp.model.Contact;
import com.brutcode.seedapp.model.RealtyContainer;
import com.brutcode.seedapp.response.ContactResponse;

import dagger.Module;
import dagger.Provides;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by Danilo on 04/07/2016.
 */
@Module
public class ZapTestApiModule {

    public interface ZapTestInterface {
        @GET("/imoveis/{codRealty}")
        Observable<Response<RealtyContainer>> getDetail(@Path("codRealty") int codRealty);

        @GET("/imoveis")
        Observable<Response<RealtyContainer>> getList();

        @POST("/imoveis/contato")
        Observable<Response<ContactResponse>> postContact(@Body Contact contact);
    }


    @Provides
    @UserScope // needs to be consistent with the component scope
    public ZapTestInterface providesGitHubInterface(Retrofit retrofit) {
        return retrofit.create(ZapTestInterface.class);
    }
}
