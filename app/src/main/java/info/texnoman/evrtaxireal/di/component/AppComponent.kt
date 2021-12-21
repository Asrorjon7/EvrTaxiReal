package info.texnoman.evrtaxireal.di.component

import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import info.texnoman.evrtaxireal.di.module.*
import info.texnoman.evrtaxireal.main.MainApplication
import javax.inject.Singleton


@Singleton
@Component(
    modules = [
        ActivityBuilder::class,
        AppModule::class,
        NetworkModule::class,
        ViewModelModule::class,
        AndroidSupportInjectionModule::class,
        FragmentModule::class
    ]
)
interface AppComponent:AndroidInjector<MainApplication> {
    @Component.Builder
    interface Builder{
        @BindsInstance
        fun application(application: MainApplication):Builder
        fun build():AppComponent
    }
}
/*
@Component.Factory
interface Factory {
    fun create(
        @BindsInstance
        network: NetworkModule,
        @BindsInstance
        sharedPrefs: ViewModelModule
    ): AppComponent
}*/