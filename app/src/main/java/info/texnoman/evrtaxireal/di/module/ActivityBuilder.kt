package info.texnoman.evrtaxireal.di.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import info.texnoman.evrtaxireal._user.main.UserActivity
import info.texnoman.evrtaxireal.main.MainActivity

@Module
abstract class ActivityBuilder {
    @ContributesAndroidInjector
    abstract fun contributesMainActivity(): MainActivity

    @ContributesAndroidInjector
    abstract fun contributesUserActivity(): UserActivity
}