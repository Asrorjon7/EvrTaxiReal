package info.texnoman.evrtaxireal.auth.ui.ChooseLanguage

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import info.texnoman.evrtaxireal.R
import info.texnoman.evrtaxireal.auth.ui.viewmodel.AuthViewModel
import info.texnoman.evrtaxireal.base.BaseFragment
import info.texnoman.evrtaxireal.databinding.FragmentChooseLanguageBinding
import info.texnoman.evrtaxireal.di.factory.injectViewModel
import info.texnoman.evrtaxireal.utils.changeColorBackGround
import info.texnoman.evrtaxireal.utils.changeGreenBackground
import info.texnoman.evrtaxireal.utils.changeGreyBackground
import info.texnoman.evrtaxireal.utils.changeTextColor
import info.texnoman.evrtaxireal.utils.shared.LanguageType
import java.util.*

class ChooseLanguageFragment : BaseFragment<FragmentChooseLanguageBinding,AuthViewModel>() {
    private var isFirstOpen = true
    override fun injectViewModel() {
        mViewModel =injectViewModel(viewModelFactory)
    }
    override fun getViewModelClass(): Class<AuthViewModel> =AuthViewModel::class.java
    override fun init() {
        changeLanguage()
        setLang("ru")
        binding.btnConfirm.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.chooseUoDFragment)
           // requireActivity().recreate()
        }
    }
    private fun changeLanguage() {
        binding.apply {
            lvRusskiy.setOnClickListener {
                changeColorBackGround(lvRusskiy,lvUzbekKrill,lvUzbekLotin)
                changeTextColor(tvRusskiy,tvUzbekKrill,tvUzbekLotin)
                if (prefs.language != "ru") {
                    setLanguage("ru")
                    if (!isFirstOpen) {
                    //    requireActivity().recreate()
                    }
                }
            }
            lvUzbekKrill.setOnClickListener {
                changeColorBackGround(lvUzbekKrill,lvRusskiy,lvUzbekLotin)
                changeTextColor(tvUzbekKrill,tvRusskiy,tvUzbekLotin)

                if (prefs.language != "ja") {
                    setLanguage("ja")
                    if (!isFirstOpen) {
                    //    requireActivity().recreate()
                    }
                }
            }
            lvUzbekLotin.setOnClickListener {
                changeColorBackGround(lvUzbekLotin,lvUzbekKrill,lvRusskiy)
                changeTextColor(tvUzbekLotin,tvUzbekKrill,tvRusskiy)

                if (prefs.language != "en") {
                    setLanguage("en")
                    if (!isFirstOpen) {
                    //    requireActivity().recreate()
                    }
                }
            }
        }
    }
    private fun setLang(language: String) {
        val dm = resources.displayMetrics
        val conf = resources.configuration
        val locale = Locale(language)
        conf.setLocale(locale)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            requireActivity().createConfigurationContext(conf) //for Android 7+
        } else {
            resources.updateConfiguration(conf, dm) //for Android 6-
        }
    }

    override fun setupViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentChooseLanguageBinding = FragmentChooseLanguageBinding.inflate(inflater,container,false)

}