package info.texnoman.evrtaxireal.network

import info.texnoman.evrtaxireal.auth.model.*
import info.texnoman.evrtaxireal.auth.request.ConfirmRequest
import info.texnoman.evrtaxireal.auth.request.EntryNumberRequest
import info.texnoman.evrtaxireal.auth.request.SignRequest
import info.texnoman.evrtaxireal.base.BaseResponse
import info.texnoman.evrtaxireal.model.request.SetCarAboutRequest
import info.texnoman.evrtaxireal.model.request.SetNewOrderRequest
import info.texnoman.evrtaxireal.model.response.CarAboutResponse
import info.texnoman.evrtaxireal.model.response.NewOrderResponse
import info.texnoman.evrtaxireal.model.response.RegionResponse
import retrofit2.Response
import retrofit2.http.*

interface ApiService {

    companion object {
        const val BASE_URL = "http://evrtaxi.uz/api/v1/"
        const val YandexURl = "https://geocode-maps.yandex.ru"
    }

    @POST("user/phone")
    suspend fun entryNumber(@Body phoneNumber: EntryNumberRequest): Response<BaseResponse<EntryNumberResponse>>

    @POST("user/complete-account")
    suspend fun signUp(@Body request: SignRequest): Response<BaseResponse<SignResponse>>

    @POST("user/confirm")
    suspend fun confirmNumber(@Body confirmRequest: ConfirmRequest): Response<BaseResponse<ConfirmResponse>>

    @GET("data/country")
    suspend fun getCountry(): Response<BaseResponse<ArrayList<CountryModel>>>

    @GET("data/state")
    suspend fun getRegion(@Query("country_id") countryId: Int): Response<BaseResponse<ArrayList<RegionResponse>>>

    @GET("data/district")
    suspend fun getDistrict(@Query("state_id") id: Int): Response<BaseResponse<ArrayList<RegionResponse>>>

    @GET("data/car-brand")
    suspend fun getCarBrand(): Response<BaseResponse<ArrayList<CarBrand>>>

    @GET("data/car-model")
    suspend fun getCarModel(@Query("brand_id") brand_id: Int): Response<BaseResponse<ArrayList<CarBrand>>>

    @POST("passenger/new-order")
    suspend fun setNewOrder(@Header("Authorization") auth: String, @Body setOrder: SetNewOrderRequest): Response<BaseResponse<NewOrderResponse>>

    suspend fun setCarAboutInfo(@Body setCarRequest: SetCarAboutRequest): Response<BaseResponse<CarAboutResponse>>

}