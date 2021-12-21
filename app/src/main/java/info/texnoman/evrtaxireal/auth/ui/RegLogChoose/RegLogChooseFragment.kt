package info.texnoman.evrtaxireal.auth.ui.RegLogChoose
import android.view.*
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import info.texnoman.evrtaxireal.R
import info.texnoman.evrtaxireal.auth.ui.viewmodel.AuthViewModel
import info.texnoman.evrtaxireal.base.BaseFragment
import info.texnoman.evrtaxireal.databinding.FragmentChooseRolBinding
import info.texnoman.evrtaxireal.di.factory.injectViewModel
import info.texnoman.evrtaxireal.main.MainActivity

class RegLogChooseFragment : BaseFragment<FragmentChooseRolBinding, AuthViewModel>() {

    override fun setupViewBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentChooseRolBinding {
        return FragmentChooseRolBinding.inflate(inflater, container, false)
    }

    override fun init() {
       // (requireActivity() as MainActivity).hideActionBar()
        IntentOtish()
        setTitle("Salomlar")
    }
    fun IntentOtish() {
        binding.apply {
            btnDriver.setOnClickListener {
                findNavController().navigate(R.id.chooseUoDFragment)
            }

            btnPassanger.setOnClickListener {
             findNavController().navigate(R.id.chooseUoDFragment)
            }
        }
    }
    override fun injectViewModel() {
        mViewModel = injectViewModel(viewModelFactory)
    }

    override fun getViewModelClass(): Class<AuthViewModel> = AuthViewModel::class.java

}


