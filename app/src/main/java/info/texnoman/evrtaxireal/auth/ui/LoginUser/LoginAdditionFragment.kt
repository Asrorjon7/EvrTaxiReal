package info.texnoman.evrtaxireal.auth.ui.LoginUser

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import info.texnoman.evrtaxireal.R
import info.texnoman.evrtaxireal._driver.main.DriverActivity
import info.texnoman.evrtaxireal._user.main.UserActivity
import info.texnoman.evrtaxireal.auth.request.SignRequest
import info.texnoman.evrtaxireal.auth.ui.viewmodel.AuthViewModel
import info.texnoman.evrtaxireal.base.BaseFragment
import info.texnoman.evrtaxireal.databinding.FragmentLoginBinding
import info.texnoman.evrtaxireal.di.factory.injectViewModel
import info.texnoman.evrtaxireal.utils.*
import info.texnoman.evrtaxireal.utils.Extensions.afterTextChangedNull
import info.texnoman.evrtaxireal.utils.Extensions.validate
import info.texnoman.evrtaxireal.utils.shared.DrivePassanger

class LoginAdditionFragment : BaseFragment<FragmentLoginBinding, AuthViewModel>() {

    override fun injectViewModel() {
        mViewModel = injectViewModel(viewModelFactory)
    }
    var sexType: Int = 1
    override fun setupViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentLoginBinding {
        return FragmentLoginBinding.inflate(inflater, container, false)
    }

    override fun getViewModelClass(): Class<AuthViewModel> = AuthViewModel::class.java
    override fun init() {
        token = arguments?.getString("token").toString()
        typeSex()
        confirmFragment()
        binding.apply {
          var a=  etNameInput.afterTextChangedNull{
              Log.e("salom",it.toString())
            }
            Log.e("sdad",a.toString())
            etSurnameInput.afterTextChangedNull {
                Log.e("salom",it.toString())
            }
        }
        /*var name=  binding.etNameInput.validate("") { s -> s.length in 3..30 }
         var surname =binding.etSurnameInput.validate(""){s-> s.length in 3..30}*/
    // binding.btnNext.isEnabled = name&&surname
        //Log.e("editTextlar",name.toString())
    }
    private fun typeSex() {
        binding.apply {
            radioGroup.setOnCheckedChangeListener { group, checkedId ->
                when (checkedId) {
                    R.id.radioTypeMale -> {
                        sexType = 1
                        mViewModel.setLoginChangeImage(true)
                    }
                    R.id.radioTypeFemale -> {
                        sexType = 0
                        mViewModel.setLoginChangeImage(false)
                    }
                }
            }

            viewModel.changeLoginImage.observe(viewLifecycleOwner) {
                if (it) {
                    ivLogin.setImage(R.drawable.login_men)
                } else {
                    ivLogin.setImage(R.drawable.login_women)
                }
            }
        }
    }

    private fun confirmFragment() {
        binding.apply {
            btnNext.setOnClickListener {
                //  var et = etNameInput.validate("Eng kamida 3 ta harf bo'lishi kerak") { s -> s.length in 3..30 }
                var surname = etSurnameInput.text.toString()
                var name = etNameInput.text.toString()
              var  token =SaveUserInformation.getAuthInfo().token.toString()

                if (DrivePassanger.getTypeService() == PassangerOrDrive.Driver) {
                viewModel.registration(SignRequest(sexType, name, token, surname,5))
                }else{
                    viewModel.registration(SignRequest(sexType, name, token, surname,1))
                }

                viewModel.register.observe(viewLifecycleOwner) { result ->
                    when (result.status) {
                        Status.ERROR ->result.message.let{
                            progressBar.gone()
                            btnNext.visible()
                            Toast.makeText(requireContext(), it.toString()+"", Toast.LENGTH_SHORT).show()
                        }
                        Status.LOADING -> {
                            progressBar.visible()
                            btnNext.gone()
                            requireView().hideKeyboard()
                        }
                        Status.SUCCESS -> result.data.let {
                          Log.e("responselar",it.toString())
                            if (it?.status == 200) {
                                it.data.let { response ->

                                    if (it.code==2){
                                        Navigation.findNavController(requireView()).navigate(R.id.carAboutInfoFragment)
                                    }else if (it.code==10){
                                    if (DrivePassanger.getTypeService() == PassangerOrDrive.Driver) {
                                        startActivity(Intent(requireContext(), DriverActivity::class.java))
                                    } else {
                                        startActivity(Intent(requireContext(), UserActivity::class.java)) }
                                    }
                                }
                            } else {
                                Toast.makeText(requireContext(), it?.message.toString() + "", Toast.LENGTH_SHORT).show()
                            }

                        }
                    }
                }
            }
        }
    }
}