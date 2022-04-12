package info.texnoman.evrtaxireal.network

import info.texnoman.evrtaxireal._user.model.response.RegionResponse
import info.texnoman.evrtaxireal.auth.model.ConfirmResponse
import info.texnoman.evrtaxireal.auth.model.EntryNumberResponse
import info.texnoman.evrtaxireal.auth.model.SignResponse
import info.texnoman.evrtaxireal.auth.request.ConfirmRequest
import info.texnoman.evrtaxireal.auth.request.EntryNumberRequest
import info.texnoman.evrtaxireal.auth.request.SignRequest
import info.texnoman.evrtaxireal.base.BaseResponse
import retrofit2.Response
import retrofit2.http.*

interface ApiService {
    companion object {
        const val BASE_URL = "http://evrtaxi.uz/api/v1/"
    }
    @POST("driver/phone")
    suspend fun entryNumber(@Body phoneNumber:EntryNumberRequest) : Response<BaseResponse<EntryNumberResponse>>

    @POST("driver/account")
    suspend fun signUp(@Body request: SignRequest) : Response<BaseResponse<SignResponse>>

    @POST("driver/confirm")
     suspend fun confirmNumber(@Body confirmRequest: ConfirmRequest):Response<BaseResponse<ConfirmResponse>>

     @GET("region")
     suspend fun getRegion(@Header("Authorization") auth: String):Response<BaseResponse<ArrayList<RegionResponse>>>

     @GET("district")
     suspend fun getDistrict(@Header("Authorization") auth: String,@Query("region_id")id:Int):Response<BaseResponse<ArrayList<RegionResponse>>>
}