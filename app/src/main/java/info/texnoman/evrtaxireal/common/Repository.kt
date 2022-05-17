package info.texnoman.evrtaxireal.common
import info.texnoman.evrtaxireal.auth.request.ConfirmRequest
import info.texnoman.evrtaxireal.auth.request.EntryNumberRequest
import info.texnoman.evrtaxireal.auth.request.SignRequest
import info.texnoman.evrtaxireal.base.BaseDataSource
import info.texnoman.evrtaxireal.model.request.SetCarAboutRequest
import info.texnoman.evrtaxireal.model.request.SetNewOrderRequest
import info.texnoman.evrtaxireal.network.ApiService
import javax.inject.Inject
class Repository @Inject constructor(private val apiService: ApiService) : BaseDataSource() {
    suspend fun signUp(signRequest: SignRequest) = getResult {
        apiService.signUp(signRequest)
    }

    suspend fun confirmNumber(confirmResponse: ConfirmRequest) = getResult {
        apiService.confirmNumber(confirmResponse)
    }

    suspend fun entryNumber(entryNumberRequest: EntryNumberRequest) = getResult {
        apiService.entryNumber(entryNumberRequest)
    }

    suspend fun getCountry() = getResult {
        apiService.getCountry()
    }

    suspend fun getRegion(countryId: Int) = getResult {
        apiService.getRegion(countryId)
    }

    suspend fun getDistrict(stateId: Int) = getResult {
        apiService.getDistrict(stateId)
    }

    suspend fun getCarBrand() = getResult {
        apiService.getCarBrand()
    }

    suspend fun getCarModel(id: Int) = getResult {
        apiService.getCarModel(id)
    }

    suspend fun setNewOrder(token:String,order: SetNewOrderRequest) = getResult {
        apiService.setNewOrder("Bearer $token",order)
    }

    suspend fun setCarAboutInfo(carAboutInfo:SetCarAboutRequest)=getResult {
        apiService.setCarAboutInfo(carAboutInfo)
    }
}