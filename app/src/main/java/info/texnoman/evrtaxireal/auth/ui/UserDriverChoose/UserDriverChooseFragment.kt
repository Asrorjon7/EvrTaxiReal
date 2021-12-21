package info.texnoman.evrtaxireal.auth.ui.UserDriverChoose

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import info.texnoman.evrtaxireal.R
import info.texnoman.evrtaxireal.auth.ui.LoginUser.LoginFragment
import info.texnoman.evrtaxireal.auth.ui.viewmodel.AuthViewModel
import info.texnoman.evrtaxireal.base.BaseFragment
import info.texnoman.evrtaxireal.databinding.FragmentChooseUoDBinding
import info.texnoman.evrtaxireal.di.factory.injectViewModel
import info.texnoman.evrtaxireal.main.MainActivity

/*
* U - degani User degani
* D-degani Driver degani
*
* */
class UserDriverChooseFragment:BaseFragment<FragmentChooseUoDBinding, AuthViewModel>() {

    override fun setupViewBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentChooseUoDBinding {
        return FragmentChooseUoDBinding.inflate(inflater, container, false)
    }
    override fun injectViewModel() {
        mViewModel = injectViewModel(viewModelFactory)
    }
    override fun getViewModelClass(): Class<AuthViewModel> = AuthViewModel::class.java
    override fun init() {
        setTitle("Salomlar")
        (requireActivity() as MainActivity).showActionBar()
        IntentFragment()
    }

    private fun IntentFragment() {
        binding.apply {
            btnDriver.setOnClickListener{
                Navigation.findNavController(requireView()).navigate(R.id.loginFragment)
            }
            btnPassanger.setOnClickListener{
                Navigation.findNavController(requireView()).navigate(R.id.loginFragment)
            }
        }

    }


}