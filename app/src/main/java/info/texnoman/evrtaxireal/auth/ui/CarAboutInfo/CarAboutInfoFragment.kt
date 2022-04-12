package info.texnoman.evrtaxireal.auth.ui.CarAboutInfo
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.core.content.ContextCompat
import com.chivorn.smartmaterialspinner.SmartMaterialSpinner
import info.texnoman.evrtaxireal.R
import info.texnoman.evrtaxireal._user.model.response.RegionResponse
import info.texnoman.evrtaxireal._user.viewmodel.UserViewModel
import info.texnoman.evrtaxireal.base.BaseFragment
import info.texnoman.evrtaxireal.databinding.FragmentCarAboutInfoBinding
import info.texnoman.evrtaxireal.di.factory.injectViewModel
import info.texnoman.evrtaxireal.utils.Status
import info.texnoman.evrtaxireal.utils.TypeService
import info.texnoman.evrtaxireal.utils.changeColor
import info.texnoman.evrtaxireal.utils.getRegionNameList


class CarAboutInfoFragment : BaseFragment<FragmentCarAboutInfoBinding,UserViewModel> (){
    var danRegionList = ArrayList<RegionResponse>()   //-->Viloyat yoki davlatni saqlab turish uchun list
    var gaRegionList = ArrayList<RegionResponse>()

    override fun setupViewBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentCarAboutInfoBinding  = FragmentCarAboutInfoBinding.inflate(inflater,container,false)
    override fun injectViewModel() {
    mViewModel =injectViewModel(viewModelFactory)

    }
    override fun getViewModelClass(): Class<UserViewModel>  =UserViewModel::class.java
    override fun init() {
        getRegion()
        binding.apply {
            setOnItemSelectedListener(spinRegion2,spinRegion1)
        }
        typeService()
    }

    private fun getRegion() {
        viewModel.fungetRegion(token)
        viewModel.getRegion.observe(viewLifecycleOwner) { result ->
            when (result.status) {
                Status.SUCCESS -> result.data.let {
                    danRegionList.addAll(it?.data!!)
                    gaRegionList.addAll(it.data!!)
                    /**
                     * Qayerdan borishini tanlash
                     */
                    binding.spinRegion1.item = getRegionNameList(it.data!!) as List<Any>?
                    binding.spinRegion1.setSelection(7)
                    /**
                     * Qayerga borishini tanlash
                     */
                    binding.spinRegion2.item = getRegionNameList(it.data!!) as List<Any>?
                    binding.spinRegion2.setSelection(2)

                }
                Status.LOADING -> {
                }
                Status.ERROR -> {
                }
            }
        }
    }

    private fun typeService() {
        binding.apply {
        radioGroup.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.radioInUzbekistan-> {
                     radioInUzbekistan.changeColor(R.color.colorPrimary)
                    radioNational.changeColor(R.color.radio_flat_text_selector)
                }
                R.id.radioNational-> {
                    radioInUzbekistan.changeColor(R.color.radio_flat_text_selector)
                    radioNational.changeColor(R.color.colorPrimary)

                }
            }
        }
        }
    }
    private fun setOnItemSelectedListener(vararg spinners: SmartMaterialSpinner<*>) {
        for (spinner in spinners) {
            spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    adapterView: AdapterView<*>?,
                    view: View,
                    position: Int,
                    id: Long
                ) {
                    spinner.hint = null
                    when (spinner.id) {
                        R.id.spinRegion1 -> {

                        }

                        R.id.spinRegion1 -> {

                       }

                    }

                }
                override fun onNothingSelected(adapterView: AdapterView<*>?) {
                    spinner.errorText = "On Nothing Selected"
                }
            }
        }
    }



}