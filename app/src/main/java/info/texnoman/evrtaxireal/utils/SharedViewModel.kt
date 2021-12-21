package info.texnoman.evrtaxireal.utils

import android.app.Application
import androidx.lifecycle.MutableLiveData
import info.texnoman.evrtaxireal.base.BaseViewModel
import javax.inject.Inject

class SharedViewModel @Inject constructor(application: Application): BaseViewModel(application = application) {
    val pageTitle = MutableLiveData<String>()
    val isLangChanged = MutableLiveData<Boolean>()
    fun setPageTitle(title: String?) {
        pageTitle.value = title!!
    }
}
