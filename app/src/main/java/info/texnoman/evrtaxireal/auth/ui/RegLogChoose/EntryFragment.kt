package info.texnoman.evrtaxireal.auth.ui.RegLogChoose

import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.navigation.Navigation
import com.hbb20.CountryCodePicker
import info.texnoman.evrtaxireal.R
import info.texnoman.evrtaxireal.auth.request.EntryNumberRequest
import info.texnoman.evrtaxireal.auth.ui.viewmodel.AuthViewModel
import info.texnoman.evrtaxireal.base.BaseFragment
import info.texnoman.evrtaxireal.databinding.FragmentEntryBinding
import info.texnoman.evrtaxireal.di.factory.injectViewModel
import info.texnoman.evrtaxireal.utils.*

class EntryFragment : BaseFragment<FragmentEntryBinding, AuthViewModel>() {
    override fun setupViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentEntryBinding {
        return FragmentEntryBinding.inflate(inflater, container, false)
    }

    override fun init() {
        // (requireActivity() as MainActivity).hideActionBar()
        IntentOtish()
        setTitle("Salomlar")

        binding.codePicker.changeDefaultLanguage(CountryCodePicker.Language.UZBEK)

        binding.tvTerms.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.termsFragment)
        }
        binding.checkTerm.setOnCheckedChangeListener { buttonView, isChecked ->
            binding.btnRegister.isEnabled = isChecked
        }
        binding.layoutCheck.setOnClickListener {
            binding.checkTerm.isChecked = !binding.checkTerm.isChecked
            binding.btnRegister.isEnabled = binding.checkTerm.isChecked
        }
    }

    fun IntentOtish() {
        binding.apply {
            btnRegister.setOnClickListener {
                var phone = etNumberInput.replace()
                var code = binding.codePicker.selectedCountryCodeWithPlus.toString()
                if (etNumberInput.replace().length != 12) {
                    etNumberInput.error = "Kiriting"
                    return@setOnClickListener
                }
                viewModel.funEntryNumber(EntryNumberRequest(code + " " + phone))
                viewModel.entryNumber.observe(viewLifecycleOwner) { result ->
                    when (result.status) {
                        Status.SUCCESS -> result.data.let { response ->

                            Log.e("response", response?.message.toString())
                            if (response?.success == true) {
                                var action =
                                    EntryFragmentDirections.actionChooseLoRFragmentToConfirmNumberFragment(
                                        code + " " + phone
                                    )
                                Navigation.findNavController(requireView()).navigate(action)
                                progressBar.gone()
                                requireView().hideKeyboard()
                                btnRegister.visible()
                            } else {
                                tvError.error(response?.message.toString())
                                Toast.makeText(
                                    requireContext(),
                                    response?.message.toString() + "",
                                    Toast.LENGTH_SHORT
                                ).show()
                                progressBar.gone()
                                btnRegister.visible()
                            }
                        }
                        Status.LOADING -> {
                            progressBar.visible()
                            btnRegister.gone()
                            requireView().hideKeyboard()
                        }
                        Status.ERROR -> {
                            tvError.error(result.data?.message.toString())
                            progressBar.gone()
                            btnRegister.visible()
                            requireView().hideKeyboard()
                        }
                    }
                }

                /*  LoginRegister.saveRegLog(RegorLog.Register)
                  findNavController().navigate(R.id.chooseUoDFragment)*/
            }
            /* btnLogin.setOnClickListener {
                 LoginRegister.saveRegLog(RegorLog.Login)
                 findNavController().navigate(R.id.chooseUoDFragment)
             }*/
        }
    }

    override fun injectViewModel() {
        mViewModel = injectViewModel(viewModelFactory)
    }

    override fun getViewModelClass(): Class<AuthViewModel> = AuthViewModel::class.java

}


