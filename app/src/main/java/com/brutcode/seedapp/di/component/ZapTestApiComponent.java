package com.brutcode.seedapp.di.component;

import com.brutcode.seedapp.ui.result.DetailScrollingActivity;
import com.brutcode.seedapp.di.module.ZapTestApiModule;
import com.brutcode.seedapp.di.scope.UserScope;
import com.brutcode.seedapp.presenter.ContactDialogPresenter;
import com.brutcode.seedapp.presenter.DetailPresenter;
import com.brutcode.seedapp.presenter.MainPresenter;
import com.brutcode.seedapp.ui.main.MainActivity;

import dagger.Component;

/**
 * Created by Danilo on 04/07/2016.
 */
@UserScope // using the previously defined scope, note that @Singleton will not work
@Component(dependencies = NetComponent.class, modules = ZapTestApiModule.class)
public interface ZapTestApiComponent {
    void inject(MainActivity activity);
    void inject(DetailScrollingActivity activity);
    void inject(DetailPresenter presenter);
    void inject(ContactDialogPresenter presenter);
    void inject(MainPresenter presenter);
}
