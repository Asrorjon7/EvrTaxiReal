package info.texnoman.evrtaxireal._user.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import info.texnoman.evrtaxireal.R
import info.texnoman.evrtaxireal._user.model.NavigationModel
import info.texnoman.evrtaxireal.base.BaseViewModel
import info.texnoman.evrtaxireal.utils.CheckUzbOrWorld
import info.texnoman.evrtaxireal.utils.TypeService
import javax.inject.Inject

class UserViewModel @Inject constructor(application: Application):BaseViewModel(application) {
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


}