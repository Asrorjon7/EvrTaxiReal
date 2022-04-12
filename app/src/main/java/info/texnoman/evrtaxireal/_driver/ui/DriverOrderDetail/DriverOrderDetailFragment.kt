package info.texnoman.evrtaxireal._driver.ui.DriverOrderDetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import info.texnoman.evrtaxireal.R
import info.texnoman.evrtaxireal._driver.viewmodel.DriverViewmodel
import info.texnoman.evrtaxireal.base.BaseFragment
import info.texnoman.evrtaxireal.databinding.FragmentDriverOrderBinding
import info.texnoman.evrtaxireal.databinding.FragmentDriverOrderDetailBinding
import info.texnoman.evrtaxireal.di.factory.injectViewModel

class DriverOrderDetailFragment : BaseFragment<FragmentDriverOrderDetailBinding,DriverViewmodel>() {

    override fun injectViewModel() {
        mViewModel =injectViewModel(viewModelFactory)
    }
    override fun getViewModelClass(): Class<DriverViewmodel> =DriverViewmodel::class.java

    override fun init() {

    }

    override fun setupViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentDriverOrderDetailBinding = FragmentDriverOrderDetailBinding.inflate(inflater,container,false)
}