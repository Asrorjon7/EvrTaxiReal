package info.texnoman.evrtaxireal.base
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.preference.PreferenceManager
import android.util.Log
import android.view.LayoutInflater
import android.view.inputmethod.InputMethodManager
import androidx.lifecycle.*
import androidx.viewbinding.ViewBinding
import com.google.gson.Gson
import com.gun0912.tedpermission.coroutine.TedPermission
import dagger.android.support.DaggerAppCompatActivity
import info.texnoman.evrtaxireal.R
import info.texnoman.evrtaxireal.di.factory.ViewModelFactory
import info.texnoman.evrtaxireal.utils.Constants
import info.texnoman.evrtaxireal.utils.PrefsHelper
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

abstract class BaseActivity<B : ViewBinding, V : ViewModel> : DaggerAppCompatActivity(),LifecycleObserver {

    private val gson: Gson = Gson()
    companion object {
        const val LOCATION_PERMISSION_REQUEST = 101

    }
    lateinit var prefs: PrefsHelper
    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    protected lateinit var mViewModel: V
    private var _binding: B? = null
    protected val binding get() = requireNotNull(_binding)
    val viewModel: V get() = mViewModel
    abstract fun injectViewModel()
    abstract fun getViewModelClass(): Class<V>

/*
    @LayoutRes
    abstract fun getLayoutResourceId(): Int*/
    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        injectViewModel()
    _binding =setupViewBinding(layoutInflater)
        setContentView(binding.root)
    lifecycle.addObserver(this)
    init()
    permissionLocation()


    }

    private fun permissionLocation() {
        lifecycleScope.launch {

            val permissionResult = TedPermission.create()
                .setRationaleTitle(R.string.rationale_title)
                .setRationaleMessage(R.string.rationale_message)
                .setDeniedTitle("Permission denied")
                .setDeniedMessage(
                    "If you reject permission,you can not use this service\n\nPlease turn on permissions at [Setting] > [Permission]"
                )
                .setGotoSettingButtonText("bla bla")
                .setPermissions(
                   android.Manifest.permission.ACCESS_FINE_LOCATION,
                    android.Manifest.permission.ACCESS_COARSE_LOCATION
                )
                .check()

            Log.d("ted", "permissionResult: $permissionResult")
        }
    }
    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    private fun clearViewBinding() {
        _binding = null
        lifecycle.removeObserver(this)
    }
    abstract fun init()
    override fun onDestroy() {
        super.onDestroy()
        hideKeyboard()
    }
    override fun onBackPressed() {
            super.onBackPressed()
    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
    fun hideKeyboard() {
        val view = this.currentFocus
        if (view != null) {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
            imm?.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }
    abstract fun setupViewBinding(inflater: LayoutInflater): B
    @SuppressWarnings("deprecation")
     fun setLanguage(language: String) {
       // ApiClient.restartRetrofit()
       // Constants.language = language
        prefs.language = language
        val dm = resources.displayMetrics
        val conf = resources.configuration
        val locale = Locale(language)
        conf.setLocale(locale)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            applicationContext.createConfigurationContext(conf) //for Android 7+
        } else {
            resources.updateConfiguration(conf, dm) //for Android 6-
        }
    }

    //#region Changing locale should not be lost when user closes the app. When user restarts the app,
    //app needs to load resources in the locale that was set by user last time.
    override fun attachBaseContext(context: Context) {
        prefs = PrefsHelper(gson, PreferenceManager.getDefaultSharedPreferences(context))
        super.attachBaseContext(updateBaseContextLocale(context))
    }

    /**
     * Updates locale of the base context of the [ParentActivity] when there has been a locale change.
     * NOTE: This method also needs to called in [onCreate] function of the [ParentActivity] so that
     * Android 6- versions will also be affected.
     */
    private fun updateBaseContextLocale(context: Context): Context {
        val locale = Locale(prefs.language ?: Constants.LANGUAGE_UZBEK)
        Locale.setDefault(locale)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return updateResourcesLocale(context, locale)
        }
        return updateResourcesLocaleLegacy(context, locale)
    }

    /**
     * Updates resources for versions Android 7+
     */
    private fun updateResourcesLocale(context: Context, locale: Locale): Context {
        val conf = context.resources.configuration
        conf.setLocale(locale)
        return context.createConfigurationContext(conf)
    }

    /**
     * Updates resources for versions Android 6-
     */
    private fun updateResourcesLocaleLegacy(context: Context, locale: Locale): Context {
        val resources = context.resources
        val conf = resources.configuration
        conf.setLocale(locale)
        resources.updateConfiguration(conf, resources.displayMetrics)
        return context
    }

}