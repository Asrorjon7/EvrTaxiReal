package info.texnoman.evrtaxireal.common
import okhttp3.Interceptor
import okhttp3.Response
class AuthInterceptor constructor(
    private val tokenProvider: () -> String
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response = chain.run {
        proceed(
            request()
                .newBuilder()
                .addHeader("Accept", "application/json")
                .addHeader("Authorization", "Bearer ${tokenProvider.invoke()}")
                .build()
        )
    }
}