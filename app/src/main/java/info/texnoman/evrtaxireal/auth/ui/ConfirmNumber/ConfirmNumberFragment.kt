package info.texnoman.evrtaxireal.auth.ui.ConfirmNumber
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import info.texnoman.evrtaxireal.R
import info.texnoman.evrtaxireal._driver.main.DriverActivity
import info.texnoman.evrtaxireal._user.main.UserActivity
import info.texnoman.evrtaxireal.auth.request.ConfirmRequest
import info.texnoman.evrtaxireal.auth.request.EntryNumberRequest
import info.texnoman.evrtaxireal.auth.ui.viewmodel.AuthViewModel
import info.texnoman.evrtaxireal.base.BaseFragment
import info.texnoman.evrtaxireal.databinding.FragmentConfirmNumberBinding
import info.texnoman.evrtaxireal.di.factory.injectViewModel
import info.texnoman.evrtaxireal.main.MainActivity
import info.texnoman.evrtaxireal.utils.*
import info.texnoman.evrtaxireal.utils.shared.DrivePassanger
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
class ConfirmNumberFragment : BaseFragment<FragmentConfirmNumberBinding, AuthViewModel>() {
    override fun injectViewModel() {
        mViewModel = injectViewModel(viewModelFactory)
    }
    override fun getViewModelClass(): Class<AuthViewModel> = AuthViewModel::class.java
    override fun setupViewBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentConfirmNumberBinding {
        return FragmentConfirmNumberBinding.inflate(inflater, container, false)
    }
    override fun init() {
        nextFragment()
        var args = arguments?.get("number")
        binding.etPhoneNumber.setText(args.toString())
        Ftimer()
        againSendSMS()
        binding.tvOtherNumberInput.setOnClickListener{
            Navigation.findNavController(it).popBackStack()
        }
    }

    private fun nextFragment() {
        binding.apply {
            btnNext.setOnClickListener {
               if (binding.etCode.otp.toString().length!=4){
                    binding.etCode.showError()
                   return@setOnClickListener
               }
                var code = (binding.etCode.otp.toString()).toInt()

                Log.e("token",SaveUserInformation.getAuthInfo().token.toString())
               // Log.e("codelengt",binding.etCode.rawText.toString().length.toString())
               viewModel.confirm(ConfirmRequest(code, SaveUserInformation.getAuthInfo().token))

                viewModel.confirm.observe(viewLifecycleOwner) { result ->
                    when (result.status) {
                        Status.SUCCESS -> result.data.let { response ->
                            Log.e("response",response.toString())
                            if (response?.success==true){
                            response?.data.let { it1 ->
                                Log.e("nextFragment: ",it1.toString() )
                             if (response.code == 10) {
                                    if (DrivePassanger.getTypeService() == PassangerOrDrive.Driver) {
                                        startActivity(Intent(requireContext(), DriverActivity::class.java))
                                    } else {
                                        startActivity(Intent(requireContext(), UserActivity::class.java))
                                    }
                                } else if (response.code == 1) {
                                    var action =ConfirmNumberFragmentDirections.navigateRegisterFragment(it1?.authKey.toString())
                                    Navigation.findNavController(requireView()).navigate(action)
                                }
                            }
                            }else{
                                response?.message.let {
                                    Log.e("wrong_code",it.toString())
                                    binding.tvError.gone()
                                     binding.etCode.showError()
                                 //  binding.tvError.text=it.toString()
                                    progressBar.gone()
                                    btnNext.visible()
                                  //  Toast.makeText(requireContext(), it?.code.toString(), Toast.LENGTH_SHORT).show()
                                }
                             }
                              }
                        Status.LOADING -> {
                            progressBar.visible()
                            btnNext.gone()
                            requireView().hideKeyboard()
                        }
                        Status.ERROR -> result.message.let {
                            progressBar.gone()
                            btnNext.visible()
                            Log.e("string", it.toString())
                        }
                    }
                }
            }
        }
    }
private fun againSendSMS(){
    binding.tvAgainSend.setOnClickListener {
        viewModel.funEntryNumber(EntryNumberRequest(binding.etPhoneNumber.text.toString()))
        viewModel.entryNumber.observe(viewLifecycleOwner){result->
            when(result.status){
                Status.ERROR->result.message.let{
                    Toast.makeText(requireContext(), it.toString()+"", Toast.LENGTH_SHORT).show()
                    binding.apply {
                        progressBar.gone()
                        btnNext.visible()
                    }
                }
                Status.LOADING->{
                    binding.apply {
                        progressBar.visible()
                        btnNext.gone()
                    }
                }

                Status.SUCCESS->result.data.let{response->
                   Log.e("response",response?.success.toString())
                    if (response?.success == true){
                         Ftimer()
                    }else{
                        Toast.makeText(requireContext(), response?.message.toString()+"", Toast.LENGTH_SHORT).show()
                    }
                    binding.apply {
                        progressBar.gone()
                        btnNext.visible()
                    }
                }
            }
        }
    }
}

    private fun Ftimer() {
        viewModel.timerStart(1L)
        binding.timerTextView.visible()
        binding.tvAgainSend.gone()
      // viewModel.onReset()
        viewModel.timerFlow
            .onEach { time ->
                binding.timerTextView.text = time
            }.launchIn(viewLifecycleOwner.lifecycleScope)

        viewModel.isEndFlow.onEach {
            if (it) {
                binding.timerTextView.gone()
                binding.tvAgainSend.visible()
            } else {
                binding.timerTextView.visible()
                binding.tvAgainSend.gone()
            }
        }.launchIn(viewLifecycleOwner.lifecycleScope)
    }

}