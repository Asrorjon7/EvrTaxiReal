package info.texnoman.evrtaxireal.auth.ui.viewmodel

import android.app.Application
import android.os.CountDownTimer
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import info.texnoman.evrtaxireal.auth.model.ConfirmResponse
import info.texnoman.evrtaxireal.auth.model.EntryNumberResponse
import info.texnoman.evrtaxireal.auth.model.SignResponse
import info.texnoman.evrtaxireal.auth.request.ConfirmRequest
import info.texnoman.evrtaxireal.auth.request.EntryNumberRequest
import info.texnoman.evrtaxireal.auth.request.SignRequest
import info.texnoman.evrtaxireal.base.BaseResponse
import info.texnoman.evrtaxireal.base.BaseViewModel
import info.texnoman.evrtaxireal.base.SingleLiveEvent
import info.texnoman.evrtaxireal.common.Repository
import info.texnoman.evrtaxireal.model.request.SetCarAboutRequest
import info.texnoman.evrtaxireal.model.response.CarAboutResponse
import info.texnoman.evrtaxireal.utils.Result
import info.texnoman.evrtaxireal.utils.SaveUserInformation
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named

class AuthViewModel @Inject constructor(
    application: Application,
    private val repository: Repository,
    @Named("IO") private val io: CoroutineDispatcher,
    @Named("MAIN") private val main: CoroutineDispatcher
) :
    BaseViewModel(application) {
    private var _changeLoginImage: MutableLiveData<Boolean> = MutableLiveData()
    val changeLoginImage: LiveData<Boolean> = _changeLoginImage

    private val _timerFlow: MutableStateFlow<String> = MutableStateFlow("")
    val timerFlow: StateFlow<String> get() = _timerFlow.asStateFlow()

    private val _isStartFlow: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val isStartFlow: StateFlow<Boolean> get() = _isStartFlow.asStateFlow()

    private val _isEndFlow: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val isEndFlow: StateFlow<Boolean> get() = _isEndFlow.asStateFlow()

    private val _registr = SingleLiveEvent<Result<BaseResponse<SignResponse>>>()
    val register: SingleLiveEvent<Result<BaseResponse<SignResponse>>> get() = _registr

    private val _entryNumber = SingleLiveEvent<Result<BaseResponse<EntryNumberResponse>>>()
    val entryNumber: SingleLiveEvent<Result<BaseResponse<EntryNumberResponse>>> get() = _entryNumber

    internal fun funEntryNumber(entryNumberRequest: EntryNumberRequest): SingleLiveEvent<Result<BaseResponse<EntryNumberResponse>>> {
        var data = SingleLiveEvent<Result<BaseResponse<EntryNumberResponse>>>()
        viewModelScope.launch(main) {
            try {
                data.postValue(Result.loading(null))
                delay(1_500)
                val result = async(context = io) {
                    repository.entryNumber(entryNumberRequest)
                }
                if (result.await().data?.success == true) {

                    SaveUserInformation.saveAuthInfo(Result.success(result.await()).data?.data?.data!!)
                }
                data.postValue(Result.success(result.await().data))
            } catch (e: Throwable) {
                data.postValue(Result.error(e.localizedMessage))
            }
        }
        return data

    }


    private fun setResultRegister(result: Result<BaseResponse<SignResponse>>) {
        _registr.postValue(result)
    }

    internal fun registration(signRequest: SignRequest) {
        viewModelScope.launch(main) {
            try {
                setResultRegister(Result.loading(null))
                delay(1_500)
                val result = async(context = io) {
                    repository.signUp(signRequest)
                }
                if (result.await().data?.success == true) {
                    var token = Result.success(result.await()).data?.data?.data?.authKey
                    SaveUserInformation.saveAuthInfo(EntryNumberResponse(token))
                }
                setResultRegister(Result.success(result.await().data))
            } catch (e: Throwable) {
                setResultRegister(Result.error(e.localizedMessage ?: "Unknown error"))
            }
        }
    }

    private val _confirm = SingleLiveEvent<Result<BaseResponse<ConfirmResponse>>>()
    val confirm: SingleLiveEvent<Result<BaseResponse<ConfirmResponse>>> get() = _confirm

    private fun setResultConfirm(result: Result<BaseResponse<ConfirmResponse>>) {
        _confirm.postValue(result)
    }

    internal fun confirm(signRequest: ConfirmRequest) {
        viewModelScope.launch(main) {
            try {
                setResultConfirm(Result.loading(null))
                delay(1_500)
                val result = async(context = io) {
                    repository.confirmNumber(signRequest)
                }
                if (result.await().data?.success == true) {
                    var token = result.await().data?.data?.token
                    SaveUserInformation.saveAuthInfo(EntryNumberResponse(token))
                }
                setResultConfirm(Result.success(result.await().data))
            } catch (e: Throwable) {
                setResultConfirm(Result.error(e.localizedMessage ?: "Unknown error"))
            }
        }
    }









    fun setLoginChangeImage(type: Boolean) {
        _changeLoginImage.value = type
    }

    fun timerStart(minut: Long): CountDownTimer {
        _isStartFlow.value = true
        _isEndFlow.value = false
        var timer: CountDownTimer = object : CountDownTimer(minut * 60000, 1000) {
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