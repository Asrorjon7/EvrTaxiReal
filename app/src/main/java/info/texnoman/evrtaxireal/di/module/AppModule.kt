package info.texnoman.evrtaxireal.di.module

import android.app.Application
import android.content.Context
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.net.PlacesClient
import dagger.Module
import dagger.Provides
import info.texnoman.evrtaxireal.R
import info.texnoman.evrtaxireal.main.MainApplication
import javax.inject.Singleton

@Module(includes = [ViewModelModule::class])
class AppModule {
    @Provides
    @Singleton
    fun provideContext(app: MainApplication):Context =app

    @Provides
    @Singleton
    fun provideApplication(app: MainApplication): Application = app
    @Provides
    @Singleton
    fun providePlacesClient(application: Application): PlacesClient {
        Places.initialize(application, application.getString(R.string.google_maps_key))
        return Places.createClient(application)
    }
}