package info.texnoman.evrtaxireal.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import info.texnoman.evrtaxireal._driver.viewmodel.DriverViewmodel
import info.texnoman.evrtaxireal._user.viewmodel.SearchDriverViewModel
import info.texnoman.evrtaxireal._user.viewmodel.UserViewModel
import info.texnoman.evrtaxireal.auth.ui.UserDriverChoose.UserDriverViewModel
import info.texnoman.evrtaxireal.auth.ui.viewmodel.AuthViewModel
import info.texnoman.evrtaxireal.di.ViewModelKey
import info.texnoman.evrtaxireal.di.factory.ViewModelFactory
import info.texnoman.evrtaxireal.main.MainViewModel
import info.texnoman.evrtaxireal.utils.SharedViewModel

/*
* created by Asrorjon
*/
@Module
abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    internal abstract fun providesPlayerViewModel(viewModel: MainViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SharedViewModel::class)
    internal abstract fun providesSharedViewModel(viewModel: SharedViewModel): ViewModel


    @Binds
    @IntoMap
    @ViewModelKey(UserDriverViewModel::class)
    internal abstract fun providesChooseUoDViewModel(viewModel: UserDriverViewModel): ViewModel


    @Binds
    @IntoMap
    @ViewModelKey(AuthViewModel::class)
    internal abstract fun providesauthViewModel(viewModel: AuthViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(UserViewModel::class)
    internal abstract fun providesuserViewModel(viewModel: UserViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SearchDriverViewModel::class)
    internal abstract fun providessearchDriverModel(viewModel: SearchDriverViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(DriverViewmodel::class)
    internal abstract fun provideDriverModel(viewModel: DriverViewmodel): ViewModel


}