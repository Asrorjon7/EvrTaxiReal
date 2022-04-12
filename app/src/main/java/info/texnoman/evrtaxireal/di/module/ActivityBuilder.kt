package info.texnoman.evrtaxireal.di.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import info.texnoman.evrtaxireal._driver.main.DriverActivity
import info.texnoman.evrtaxireal._user.main.UserActivity
import info.texnoman.evrtaxireal.main.MainActivity
import info.texnoman.evrtaxireal.splash.SplashActivity
@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector
    abstract fun contributesSplashActivity(): SplashActivity

    @ContributesAndroidInjector
    abstract fun contributesMainActivity(): MainActivity

    @ContributesAndroidInjector
    abstract fun contributesUserActivity(): UserActivity

    @ContributesAndroidInjector
    abstract fun contributesDriveActivity(): DriverActivity
}