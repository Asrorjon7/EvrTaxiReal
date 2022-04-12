package info.texnoman.evrtaxireal.main
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import info.texnoman.evrtaxireal.di.component.DaggerAppComponent
import io.paperdb.Paper

class MainApplication: DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
     return DaggerAppComponent.builder().application(this)
         .build()
    }

    override fun onCreate() {
        super.onCreate()
      Paper.init(this)
    }
}