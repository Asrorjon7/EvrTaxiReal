package info.texnoman.evrtaxireal.network

import com.google.gson.GsonBuilder
import info.texnoman.evrtaxireal.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitClient {
    private val gson = GsonBuilder()
        .setLenient()
        .create()

    private val client = OkHttpClient.Builder().also { client ->
        if (BuildConfig.DEBUG) {
            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY
            client.addInterceptor(logging)
        }
        client.connectTimeout(30, TimeUnit.SECONDS)
        client.readTimeout(30, TimeUnit.SECONDS)
    }.build()
   /* val instance: YandexService by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(ApiService.YandexURl)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        retrofit.create(YandexService::class.java)
    }*/


}