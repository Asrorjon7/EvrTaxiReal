package info.texnoman.evrtaxireal._user.ui.Profil

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import info.texnoman.evrtaxireal.R
import info.texnoman.evrtaxireal._user.main.UserActivity
import info.texnoman.evrtaxireal._user.viewmodel.UserViewModel
import info.texnoman.evrtaxireal.base.BaseFragment
import info.texnoman.evrtaxireal.databinding.FragmentProfilEditBinding
import info.texnoman.evrtaxireal.di.factory.injectViewModel
class ProfilEditFragment() : BaseFragment<FragmentProfilEditBinding, UserViewModel>() {

    override fun injectViewModel() {
        mViewModel = injectViewModel(viewModelFactory)
    }
    override fun getViewModelClass(): Class<UserViewModel> = UserViewModel::class.java

    override fun init() {
        (requireActivity() as UserActivity).hideActionBar()
        binding.ivBack.setOnClickListener {
            Navigation.findNavController(it).popBackStack()
        }

    }
    override fun setupViewBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentProfilEditBinding {
        return FragmentProfilEditBinding.inflate(inflater, container, false)
    }
    override fun onDestroy() {
        super.onDestroy()
        (requireActivity() as UserActivity).showActionBar()
    }
}