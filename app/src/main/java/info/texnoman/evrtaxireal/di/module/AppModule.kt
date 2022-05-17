package info.texnoman.evrtaxireal.di.module

import android.app.Application
import android.content.Context
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.net.PlacesClient
import dagger.Module
import dagger.Provides
import info.texnoman.evrtaxireal.R
import info.texnoman.evrtaxireal.common.AuthInterceptor
import info.texnoman.evrtaxireal.common.Repository
import info.texnoman.evrtaxireal.main.MainApplication
import info.texnoman.evrtaxireal.network.ApiService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import okhttp3.Interceptor
import retrofit2.Retrofit
import javax.inject.Named
import javax.inject.Singleton

@Module(includes = [ViewModelModule::class])
class AppModule {

    @Singleton
    @Provides
    fun provideAuthInterceptor(sharedPreferencesSources: String): Interceptor {
        return AuthInterceptor() { sharedPreferencesSources }
    }

    @Provides
    @Singleton
    fun provideContext(app: MainApplication): Context = app

    @Provides
    @Singleton
    fun provideApplication(app: MainApplication): Application = app

    @Provides
    @Singleton
    fun providePlacesClient(application: Application): PlacesClient {
        Places.initialize(application, application.getString(R.string.google_maps_key))
        return Places.createClient(application)
    }

    @Provides
    @Singleton
    @Named("IO")
    fun provideBackgroundDispatchers(): CoroutineDispatcher =
        Dispatchers.IO

    @Provides
    @Singleton
    @Named("MAIN")
    fun provideMainDispatchers(): CoroutineDispatcher =
        Dispatchers.Main

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService =
        retrofit.create(ApiService::class.java)

    @Provides
    @Singleton
    fun provideRepository(apiService: ApiService) = Repository(apiService)
/*
    @Provides
    @Singleton
    fun provideRepositoryYandex(yandexService: YandexService) = YandexRepository(yandexService)*/

}