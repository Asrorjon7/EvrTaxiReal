package info.texnoman.evrtaxireal.base

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.inputmethod.InputMethodManager
import androidx.annotation.LayoutRes
import androidx.lifecycle.*
import androidx.viewbinding.ViewBinding
import androidx.viewbinding.ViewBindings
import com.gun0912.tedpermission.coroutine.TedPermission
import dagger.android.support.DaggerAppCompatActivity
import info.texnoman.evrtaxireal.R
import info.texnoman.evrtaxireal.di.factory.ViewModelFactory
import kotlinx.coroutines.launch
import java.util.jar.Manifest
import javax.inject.Inject

abstract class BaseActivity<B : ViewBinding, V : ViewModel> : DaggerAppCompatActivity(),LifecycleObserver {
    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private  var _binding: B? =null
    protected lateinit var mViewModel: V

    protected val binding
        get() = requireNotNull(_binding)
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
}