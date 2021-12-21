package info.texnoman.evrtaxireal.auth.ui.ConfirmNumber

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import info.texnoman.evrtaxireal.R
import info.texnoman.evrtaxireal.auth.ui.viewmodel.AuthViewModel
import info.texnoman.evrtaxireal.base.BaseFragment
import info.texnoman.evrtaxireal.databinding.FragmentConfirmNumberBinding
import info.texnoman.evrtaxireal.di.factory.injectViewModel
import info.texnoman.evrtaxireal.main.MainActivity
import info.texnoman.evrtaxireal.utils.gone
import info.texnoman.evrtaxireal.utils.visible
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class ConfirmNumberFragment : BaseFragment<FragmentConfirmNumberBinding, AuthViewModel>() {

    override fun injectViewModel() {
        mViewModel = injectViewModel(viewModelFactory)
    }
    override fun getViewModelClass(): Class<AuthViewModel> = AuthViewModel::class.java

    override fun setupViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentConfirmNumberBinding {
        return FragmentConfirmNumberBinding.inflate(inflater, container, false)
    }


    override fun init() {


        nextFragment()

        Ftimer()
    }
    private fun nextFragment() {
        binding.apply {
            btnNext.setOnClickListener {
                findNavController().navigate(R.id.carAboutInfoFragment)
            }
        }
    }
    private fun Ftimer() {
        viewModel.timerStart(1L)

        viewModel.timerFlow
            .onEach { time ->
                binding.timerTextView.text = time
            }.launchIn(viewLifecycleOwner.lifecycleScope)

        viewModel.isEndFlow.onEach {
            if (it){
                binding.timerTextView.gone()
                binding.tvAgainSend.visible()
            }else{
                binding.timerTextView.visible()
                binding.tvAgainSend.gone()
            }
        }.launchIn(viewLifecycleOwner.lifecycleScope)
    }

}