package info.texnoman.evrtaxireal._user.viewmodel

import android.app.Application
import android.location.Location
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.waheed.location.updates.livedata.LocationLiveData
import info.texnoman.evrtaxireal.R
import info.texnoman.evrtaxireal._user.model.NavigationModel
import info.texnoman.evrtaxireal._user.model.response.RegionResponse
import info.texnoman.evrtaxireal.auth.model.EntryNumberResponse
import info.texnoman.evrtaxireal.auth.request.EntryNumberRequest
import info.texnoman.evrtaxireal.base.BaseResponse
import info.texnoman.evrtaxireal.base.BaseViewModel
import info.texnoman.evrtaxireal.base.SingleLiveEvent
import info.texnoman.evrtaxireal.common.Repository
import info.texnoman.evrtaxireal.utils.CheckUzbOrWorld
import info.texnoman.evrtaxireal.utils.Result
import info.texnoman.evrtaxireal.utils.SaveUserInformation
import info.texnoman.evrtaxireal.utils.TypeService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named

class UserViewModel @Inject constructor(application: Application,
                                        private val repository: Repository,
                                        @Named("IO") private val io: CoroutineDispatcher,
                                        @Named("MAIN") private val main: CoroutineDispatcher
):BaseViewModel(application) {
    private val locationData =
        LocationLiveData(application)
    val getLocationData: LiveData<Location> = locationData



    private var _typeService: MutableLiveData<TypeService> = MutableLiveData()
    val typeService: LiveData<TypeService> = _typeService

    private var _navigationData: MutableLiveData<ArrayList<NavigationModel>> = MutableLiveData()
    val navigationData: LiveData<ArrayList<NavigationModel>> = _navigationData

    fun setTypeService(typeService:TypeService){
        _typeService.value =typeService
    }

     fun setNavigationData(){
         var list =ArrayList<NavigationModel>()
         list.add(NavigationModel(1,"Buyurtma berish", R.drawable.set_order))
         list.add(NavigationModel(2,"Buyurtmalar tarixi", R.drawable.order_history))
         list.add(NavigationModel(3,"Xalqaro buyurtma", R.drawable.arround_order))
         list.add(NavigationModel(4,"Xabarnoma", R.drawable.notif))
        _navigationData.value =list
     }



    private val _getRegion = SingleLiveEvent<Result<BaseResponse<ArrayList<RegionResponse>>>>()
    val getRegion: SingleLiveEvent<Result<BaseResponse<ArrayList<RegionResponse>>>> get()=_getRegion

    internal  fun fungetRegion(token:String){
        viewModelScope.launch(main) {
            try {
                _getRegion.postValue(Result.loading(null))
                delay(500)
                val result =async (context = io){
                    repository.getRegion(token)
                }
                _getRegion.postValue(Result.success(result.await().data))
            }
            catch (e:Throwable){
                _getRegion.postValue(Result.error(e.localizedMessage))
            }
        }

    }


    private val _getDistrict = SingleLiveEvent<Result<BaseResponse<ArrayList<RegionResponse>>>>()
    val getDistrict: SingleLiveEvent<Result<BaseResponse<ArrayList<RegionResponse>>>> get()=_getDistrict

    internal  fun fungetDistrict(token:String,id:Int){
        viewModelScope.launch(main) {
            try {
                _getDistrict.postValue(Result.loading(null))
                delay(500)
                val result =async (context = io){
                    repository.getDistrict(token,id)
                }
                _getDistrict.postValue(Result.success(result.await().data))
            }
            catch (e:Throwable){
                _getDistrict.postValue(Result.error(e.localizedMessage))
            }
        }

    }





}