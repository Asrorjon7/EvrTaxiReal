package info.texnoman.evrtaxireal._user.viewmodel

import android.app.Application
import android.location.Location
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.waheed.location.updates.livedata.LocationLiveData
import info.texnoman.evrtaxireal.R
import info.texnoman.evrtaxireal.model.NavigationModel
import info.texnoman.evrtaxireal.model.response.RegionResponse
import info.texnoman.evrtaxireal.auth.model.CarBrand
import info.texnoman.evrtaxireal.auth.model.CountryModel
import info.texnoman.evrtaxireal.auth.model.EntryNumberResponse
import info.texnoman.evrtaxireal.base.BaseResponse
import info.texnoman.evrtaxireal.base.BaseViewModel
import info.texnoman.evrtaxireal.base.SingleLiveEvent
import info.texnoman.evrtaxireal.common.Repository
import info.texnoman.evrtaxireal.model.request.SetCarAboutRequest
import info.texnoman.evrtaxireal.model.request.SetNewOrderRequest
import info.texnoman.evrtaxireal.model.response.CarAboutResponse
import info.texnoman.evrtaxireal.model.response.NewOrderResponse
import info.texnoman.evrtaxireal.utils.Result
import info.texnoman.evrtaxireal.utils.SaveUserInformation
import info.texnoman.evrtaxireal.utils.TypeService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named

class UserViewModel @Inject constructor(
    application: Application,
    private val repository: Repository,
    @Named("IO") private val io: CoroutineDispatcher,
    @Named("MAIN") private val main: CoroutineDispatcher
) : BaseViewModel(application) {
    private val locationData =
        LocationLiveData(application)
    val getLocationData: LiveData<Location> = locationData

    private var _typeService: MutableLiveData<TypeService> = MutableLiveData()
    val typeService: LiveData<TypeService> = _typeService

    private var _navigationData: MutableLiveData<ArrayList<NavigationModel>> = MutableLiveData()
    val navigationData: LiveData<ArrayList<NavigationModel>> = _navigationData

    private var _navigationDriverData: MutableLiveData<ArrayList<NavigationModel>> =
        MutableLiveData()
    val navigationDriverData: LiveData<ArrayList<NavigationModel>> = _navigationDriverData


    fun setTypeService(typeService: TypeService) {
        _typeService.value = typeService
    }

    fun setNavigationData() {
        var list = ArrayList<NavigationModel>()
        list.add(NavigationModel(1, "Buyurtma berish", R.drawable.set_order))
        list.add(NavigationModel(2, "Buyurtmalar tarixi", R.drawable.order_history))
        list.add(NavigationModel(3, "Xalqaro buyurtma", R.drawable.arround_order))
        list.add(NavigationModel(4, "Xabarnoma", R.drawable.notif))
        _navigationData.value = list
    }

    fun setDriverData() {
        var list = ArrayList<NavigationModel>()
        list.add(NavigationModel(1, "Hisobni to’ldirish", R.drawable.payment))
        list.add(NavigationModel(2, "Buyurtma berish", R.drawable.set_order))
        list.add(NavigationModel(3, "Buyurtmalar tarixi", R.drawable.order_history))
        list.add(NavigationModel(4, "Xalqaro buyurtma", R.drawable.arround_order))
        list.add(NavigationModel(5, "Xabarnoma", R.drawable.notif))
        _navigationDriverData.value = list
    }

    private val _getRegion = SingleLiveEvent<Result<BaseResponse<ArrayList<RegionResponse>>>>()
    val getRegion: SingleLiveEvent<Result<BaseResponse<ArrayList<RegionResponse>>>> get() = _getRegion

    internal fun fungetRegion(countryId: Int) {
        viewModelScope.launch(main) {
            try {
                _getRegion.postValue(Result.loading(null))
                delay(500)
                val result = async(context = io) {
                    repository.getRegion(countryId)
                }
                _getRegion.postValue(Result.success(result.await().data))
            } catch (e: Throwable) {
                _getRegion.postValue(Result.error(e.localizedMessage))
            }
        }

    }


    private val _getDistrict = SingleLiveEvent<Result<BaseResponse<ArrayList<RegionResponse>>>>()
    val getDistrict: SingleLiveEvent<Result<BaseResponse<ArrayList<RegionResponse>>>> get() = _getDistrict

    internal fun fungetDistrict(stateId: Int) {
        viewModelScope.launch(main) {
            try {
                _getDistrict.postValue(Result.loading(null))
                delay(500)
                val result = async(context = io) {
                    repository.getDistrict(stateId)
                }
                _getDistrict.postValue(Result.success(result.await().data))
            } catch (e: Throwable) {
                _getDistrict.postValue(Result.error(e.localizedMessage))
            }
        }

    }

    private val _getCarBrand = SingleLiveEvent<Result<BaseResponse<ArrayList<CarBrand>>>>()
    val getCarBrand: SingleLiveEvent<Result<BaseResponse<ArrayList<CarBrand>>>> get() = _getCarBrand

    internal fun getCardBrand() {
        viewModelScope.launch(main) {
            try {
                _getCarBrand.postValue(Result.loading(null))
                delay(500)
                val result = async(context = io) {
                    repository.getCarBrand()
                }
                _getCarBrand.postValue(Result.success(result.await().data))
            } catch (e: Throwable) {
                _getCarBrand.postValue(Result.error(e.localizedMessage))
            }
        }

    }


    private val _getCarModel = SingleLiveEvent<Result<BaseResponse<ArrayList<CarBrand>>>>()
    val getCarModel: SingleLiveEvent<Result<BaseResponse<ArrayList<CarBrand>>>> get() = _getCarModel

    internal fun getCarModel(id: Int) {
        viewModelScope.launch(main) {
            try {
                _getCarModel.postValue(Result.loading(null))
                delay(500)
                val result = async(context = io) {
                    repository.getCarModel(id)
                }
                _getCarModel.postValue(Result.success(result.await().data))
            } catch (e: Throwable) {
                _getCarModel.postValue(Result.error(e.localizedMessage))
            }
        }

    }


    private val _getCountryModel = SingleLiveEvent<Result<BaseResponse<ArrayList<CountryModel>>>>()
    val getCountryModel: SingleLiveEvent<Result<BaseResponse<ArrayList<CountryModel>>>> get() = _getCountryModel

    internal fun getCountryModel() {
        viewModelScope.launch(main) {
            try {
                _getCountryModel.postValue(Result.loading(null))
                delay(500)
                val result = async(context = io) {
                    repository.getCountry()
                }
                _getCountryModel.postValue(Result.success(result.await().data))
            } catch (e: Throwable) {
                _getCountryModel.postValue(Result.error(e.localizedMessage))
            }
        }

    }
    private val _setNewOrder = SingleLiveEvent<Result<BaseResponse<NewOrderResponse>>>()
    val setNewOrder: SingleLiveEvent<Result<BaseResponse<NewOrderResponse>>> get() = _setNewOrder

    fun setNewOrder(token:String,setOrder:SetNewOrderRequest):SingleLiveEvent<Result<BaseResponse<NewOrderResponse>>>{

       viewModelScope.launch(main) {
           try {
               _setNewOrder.postValue(Result.loading(null))
               delay(500)
               val result = async(context = io) {
                   repository.setNewOrder(token,setOrder)
               }
               _setNewOrder.postValue(Result.success(result.await().data))
           } catch (e: Throwable) {
               _setNewOrder.postValue(Result.error(e.localizedMessage))
           }
       }

        return setNewOrder
   }
    private val _setCarAbout = SingleLiveEvent<Result<BaseResponse<CarAboutResponse>>>()
    val setCarAbout: SingleLiveEvent<Result<BaseResponse<CarAboutResponse>>> get() = _setCarAbout

    internal fun setCarAboutInfo(signRequest: SetCarAboutRequest): SingleLiveEvent<Result<BaseResponse<CarAboutResponse>>> {
        viewModelScope.launch(main) {
            try {
                _setCarAbout.postValue(Result.loading(null))
                delay(1_500)
                val result = async(context = io) {
                    repository.setCarAboutInfo(signRequest)
                }
                if (result.await().data?.success == true) {
                    var token = result.await().data?.data?.authKey
                    SaveUserInformation.saveAuthInfo(EntryNumberResponse(token))
                }
                _setCarAbout.postValue(Result.success(result.await().data))
            } catch (e: Throwable) {
                _setCarAbout.postValue(Result.error(e.localizedMessage ?: "Unknown error"))
            }
        }
        return setCarAbout
    }




}