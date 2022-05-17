package info.texnoman.evrtaxireal.base
import android.os.Build
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.*
import androidx.viewbinding.ViewBinding
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import dagger.android.support.DaggerDialogFragment
import dagger.android.support.DaggerFragment
import info.texnoman.evrtaxireal.R
import info.texnoman.evrtaxireal._user.main.UserActivity
import info.texnoman.evrtaxireal.di.factory.ViewModelFactory
import info.texnoman.evrtaxireal.di.factory.injectViewModel
import info.texnoman.evrtaxireal.main.MainActivity
import info.texnoman.evrtaxireal.utils.PrefsHelper
import info.texnoman.evrtaxireal.utils.SaveUserInformation
import info.texnoman.evrtaxireal.utils.SharedViewModel
import timber.log.Timber
import java.util.*
import javax.inject.Inject

abstract class BaseFragment<B : ViewBinding, V : ViewModel> : DaggerFragment(),
    LifecycleObserver {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private val gson = Gson()

    protected lateinit var prefs: PrefsHelper
    protected lateinit var mViewModel: V
    val viewModel: V get() = mViewModel

    open lateinit var sharedViewModel: SharedViewModel
    protected lateinit var token:String

    abstract fun injectViewModel()

    abstract fun getViewModelClass(): Class<V>

    private var _binding: B? = null
    protected val binding get() = requireNotNull(_binding)

    override fun onCreate(savedInstanceState: Bundle?) {
       // setStyle(STYLE_NORMAL, R.style.AppTheme)
        super.onCreate(savedInstanceState)
        sharedViewModel =injectViewModel(viewModelFactory)
       try {
           token =SaveUserInformation.getAuthInfo().token.toString()
       }catch (e:Exception){

       }
      // mViewModel =injectViewModel(viewModelFactory)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewLifecycleOwner.lifecycle.addObserver(this)
        init()
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
     //   dialog?.window?.attributes?.windowAnimations = R.style.AppTheme_Fragment
        super.onActivityCreated(savedInstanceState)
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
          injectViewModel()
        prefs = PrefsHelper(gson, PreferenceManager.getDefaultSharedPreferences(requireContext()))

        _binding = setupViewBinding(inflater, container)
        return binding.root
    }
    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()

    }
    abstract fun init()
    abstract fun setupViewBinding(
        inflater: LayoutInflater, container: ViewGroup?
    ): B

    fun setTitle(title: String?) {
        sharedViewModel.setPageTitle(title)
    }
    protected fun snackBar(message: String) {
        Snackbar.make(binding.root, message, Snackbar.LENGTH_LONG).show()
        //Timber.e("saom",message.toString())
    }

    private fun setLang(language: String) {
        val dm = resources.displayMetrics
        val conf = resources.configuration
        val locale = Locale(language)
        conf.setLocale(locale)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            requireActivity().createConfigurationContext(conf)
        } else {
            resources.updateConfiguration(conf, dm)
        }
    }

     fun setLanguage(language: String) {
        (activity as BaseActivity<*, *>).setLanguage(language)
    }
    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    private fun clearViewBinding() {
        _binding = null
        viewLifecycleOwner.lifecycle.removeObserver(this)
    }


}