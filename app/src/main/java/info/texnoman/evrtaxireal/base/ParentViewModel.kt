package info.texnoman.evrtaxireal.base

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import javax.inject.Inject

open class BaseViewModel  @Inject constructor(application: Application):AndroidViewModel(application)