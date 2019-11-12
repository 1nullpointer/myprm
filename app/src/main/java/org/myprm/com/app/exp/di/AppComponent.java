
package org.myprm.com.app.exp.di;

import android.app.Application;

import org.myprm.com.app.exp.MyPrmApp;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;

@Singleton
@Component(modules = {
        AndroidInjectionModule.class,AppModule.class,MainActivityModule.class

})
public interface AppComponent {


    @Component.Builder
    interface Builder {
        @BindsInstance Builder application(Application application);

        AppComponent build();
    }
    void inject(MyPrmApp myPrmApp);
}
