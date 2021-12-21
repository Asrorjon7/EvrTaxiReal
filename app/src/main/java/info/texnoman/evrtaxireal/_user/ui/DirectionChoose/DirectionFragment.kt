package info.texnoman.evrtaxireal._user.ui.DirectionChoose

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import androidx.navigation.Navigation
import info.texnoman.evrtaxireal.R
import info.texnoman.evrtaxireal._user.viewmodel.UserViewModel
import info.texnoman.evrtaxireal.base.BaseFragment
import info.texnoman.evrtaxireal.databinding.FragmentDirectionFragmentBinding
import info.texnoman.evrtaxireal.di.factory.injectViewModel
import info.texnoman.evrtaxireal.utils.CheckUzbOrWorld


class DirectionFragment : BaseFragment<FragmentDirectionFragmentBinding, UserViewModel>() {

    override fun injectViewModel() {
        mViewModel = injectViewModel(viewModelFactory)
    }
    override fun getViewModelClass(): Class<UserViewModel> = UserViewModel::class.java
    override fun init() {
        nextFragment()
    }
    private fun nextFragment() {
        binding.apply {
            lvArroundUzb.setOnClickListener {
         val action = DirectionFragmentDirections.directionToSetOrder(CheckUzbOrWorld.UZB)
              Navigation.findNavController(it).navigate(action)
            }
            lvArroundWorld.setOnClickListener{
             val action=   DirectionFragmentDirections.directionToSetOrder(CheckUzbOrWorld.WORLD)
                Navigation.findNavController(it).navigate(action)
            }
        }
    }

    override fun setupViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentDirectionFragmentBinding =
        FragmentDirectionFragmentBinding.inflate(inflater, container, false)

}