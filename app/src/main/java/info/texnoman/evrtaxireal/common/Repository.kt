package info.texnoman.evrtaxireal.common

import info.texnoman.evrtaxireal.auth.request.ConfirmRequest
import info.texnoman.evrtaxireal.auth.request.EntryNumberRequest
import info.texnoman.evrtaxireal.auth.request.SignRequest
import info.texnoman.evrtaxireal.base.BaseDataSource
import info.texnoman.evrtaxireal.network.ApiService
import javax.inject.Inject

class Repository @Inject constructor(private val apiService: ApiService): BaseDataSource(){
    suspend fun signUp(signRequest: SignRequest) = getResult {
        apiService.signUp(signRequest)
    }
    suspend fun confirmNumber(confirmResponse: ConfirmRequest)=getResult {
        apiService.confirmNumber(confirmResponse)
    }
    suspend fun entryNumber(entryNumberRequest: EntryNumberRequest)=getResult {
        apiService.entryNumber(entryNumberRequest)
    }
    suspend fun getRegion(token:String)=getResult {
        apiService.getRegion("Bearer $token")
    }

    suspend fun getDistrict(token: String,id:Int)=getResult{
        apiService.getDistrict("Bearer $token",id)
    }
}