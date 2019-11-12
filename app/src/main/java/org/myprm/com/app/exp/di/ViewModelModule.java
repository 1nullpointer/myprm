package org.myprm.com.app.exp.di;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import org.myprm.com.app.exp.ui.contact.ContactViewModel;
import org.myprm.com.app.exp.viewmodel.MyPrmViewModelFactory;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
abstract class ViewModelModule {


    @Binds
    @IntoMap
    @ViewModelKey(ContactViewModel.class)
    abstract ViewModel bindContactViewModel(ContactViewModel contactViewModel);

    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(MyPrmViewModelFactory factory);
}
