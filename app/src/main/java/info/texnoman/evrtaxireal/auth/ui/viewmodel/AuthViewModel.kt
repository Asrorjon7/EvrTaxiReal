package info.texnoman.evrtaxireal.auth.ui.viewmodel

import android.app.Application
import android.os.CountDownTimer
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import info.texnoman.evrtaxireal.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class AuthViewModel @Inject constructor(application: Application) : BaseViewModel(application) {

    private var _changeLoginImage: MutableLiveData<Boolean> = MutableLiveData()
    val changeLoginImage: LiveData<Boolean> = _changeLoginImage

    private val _timerFlow: MutableStateFlow<String> = MutableStateFlow("")
    val timerFlow: StateFlow<String> get() = _timerFlow.asStateFlow()

    private val _isStartFlow: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val isStartFlow: StateFlow<Boolean> get() = _isStartFlow.asStateFlow()

    private val _isEndFlow: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val isEndFlow: StateFlow<Boolean> get() = _isEndFlow.asStateFlow()

    fun setLoginChangeImage(type: Boolean) {
        _changeLoginImage.value = type
    }

    fun timerStart(minut:Long): CountDownTimer {
        _isStartFlow.value = true
        var timer: CountDownTimer = object : CountDownTimer(minut*60000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                val sec = (millisUntilFinished / 1000) % 60
                val min = (millisUntilFinished / (1000 * 60)) % 60
                var formattedTimeStr = if (sec <= 9) {
                    "0$min : 0$sec"
                } else {
                    "0$min : $sec"
                }
                try {
                    _timerFlow.value = formattedTimeStr
                } catch (e: Exception) {
                }
            }
            override fun onFinish() {
                _isEndFlow.value = true
            }
        }
        return timer.start()
    }

    fun onReset() {
        _isStartFlow.value = false
    }


}