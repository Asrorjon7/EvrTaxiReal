package info.texnoman.evrtaxireal.auth.ui.LoginUser

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import info.texnoman.evrtaxireal.base.BaseFragment
import info.texnoman.evrtaxireal.databinding.FragmentRealLoginBinding
import info.texnoman.evrtaxireal.di.factory.injectViewModel
import info.texnoman.evrtaxireal.main.MainViewModel

class RealLoginFragment : BaseFragment<FragmentRealLoginBinding,MainViewModel>() {
    override fun injectViewModel() {
        mViewModel =injectViewModel(viewModelFactory)
    }

    override fun getViewModelClass(): Class<MainViewModel> =MainViewModel::class.java

    override fun init() {
        binding.btnNext.setOnClickListener {
            var action=   RealLoginFragmentDirections.navigateConfirmFragment("+9833847383847")
            Navigation.findNavController(it).navigate(action)
        }
    }

    override fun setupViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentRealLoginBinding = FragmentRealLoginBinding.inflate(inflater,container,false)


}