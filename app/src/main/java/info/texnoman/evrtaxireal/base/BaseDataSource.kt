package info.texnoman.evrtaxireal.base

import info.texnoman.evrtaxireal.utils.Result
import retrofit2.Response
import timber.log.Timber

abstract class BaseDataSource {
    protected suspend fun <T> getResult(call: suspend () -> Response<T>): Result<T> {
        try {
            val response = call()
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) return Result.success(body)
            }
            return error("${response.code()}")
            //: ${response.message()}
        } catch (e: Exception) {
            return error(e.message ?: e.toString())
        }
    }

    private fun <T> error(message: String): Result<T> {
        Timber.e(message)
        //ApiError(message,)
        return Result.error(message)
    }
}


