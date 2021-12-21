package info.texnoman.evrtaxireal.auth.ui.LoginUser

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import info.texnoman.evrtaxireal.R
import info.texnoman.evrtaxireal.auth.ui.viewmodel.AuthViewModel
import info.texnoman.evrtaxireal.base.BaseFragment
import info.texnoman.evrtaxireal.databinding.FragmentLoginBinding
import info.texnoman.evrtaxireal.di.factory.injectViewModel
import info.texnoman.evrtaxireal.utils.Extensions.shortText
import info.texnoman.evrtaxireal.utils.Extensions.validate
import info.texnoman.evrtaxireal.utils.setImage


class LoginFragment : BaseFragment<FragmentLoginBinding, AuthViewModel>() {
    override fun injectViewModel() {
        mViewModel = injectViewModel(viewModelFactory)
    }

    override fun setupViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentLoginBinding {
        return FragmentLoginBinding.inflate(inflater, container, false)
    }

    override fun getViewModelClass(): Class<AuthViewModel> = AuthViewModel::class.java

    override fun init() {
        typeSex()
        confirmFragment()
    }

    private fun typeSex() {
        binding.apply {
            radioGroup.setOnCheckedChangeListener { group, checkedId ->
                when (checkedId) {
                    R.id.radioTypeMale -> {
                        mViewModel.setLoginChangeImage(true)
                    }
                    R.id.radioTypeFemale -> {
                        mViewModel.setLoginChangeImage(false)
                    }
                }
            }

            viewModel.changeLoginImage.observe(viewLifecycleOwner, {
                if (it) {
                    ivLogin.setImage(R.drawable.login_men)
                } else {
                    ivLogin.setImage(R.drawable.login_women)
                }
            })

        }
    }
    private fun confirmFragment() {
        binding.apply {
            btnNext.setOnClickListener {
                var et = etNameInput.validate("Eng kamida 3 ta harf bo'lishi kerak") { s -> s.length in 3..30 }
                Log.e("text",etNumberInput.validate("To'liq kiriting"){s-> s.length in 0..11}.toString())

                    Navigation.findNavController(requireView()).navigate(R.id.confirmNumberFragment)
            }
        }
    }


}