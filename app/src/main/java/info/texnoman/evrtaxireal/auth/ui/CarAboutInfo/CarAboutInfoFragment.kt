package info.texnoman.evrtaxireal.auth.ui.CarAboutInfo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import info.texnoman.evrtaxireal.R
import info.texnoman.evrtaxireal.auth.ui.viewmodel.AuthViewModel
import info.texnoman.evrtaxireal.base.BaseFragment
import info.texnoman.evrtaxireal.databinding.FragmentCarAboutInfoBinding
import info.texnoman.evrtaxireal.di.factory.injectViewModel


class CarAboutInfoFragment : BaseFragment<FragmentCarAboutInfoBinding,AuthViewModel> (){
    override fun setupViewBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentCarAboutInfoBinding  = FragmentCarAboutInfoBinding.inflate(inflater,container,false)
    override fun injectViewModel() {
       mViewModel =injectViewModel(viewModelFactory)

    }
    override fun getViewModelClass(): Class<AuthViewModel>  =AuthViewModel::class.java
    override fun init() {

    }


}