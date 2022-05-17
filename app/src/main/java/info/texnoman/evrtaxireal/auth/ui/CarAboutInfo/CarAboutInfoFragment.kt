package info.texnoman.evrtaxireal.auth.ui.CarAboutInfo

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import com.chivorn.smartmaterialspinner.SmartMaterialSpinner
import info.texnoman.evrtaxireal.R
import info.texnoman.evrtaxireal.model.response.RegionResponse
import info.texnoman.evrtaxireal._user.viewmodel.UserViewModel
import info.texnoman.evrtaxireal.auth.model.CarBrand
import info.texnoman.evrtaxireal.auth.ui.viewmodel.AuthViewModel
import info.texnoman.evrtaxireal.base.BaseFragment
import info.texnoman.evrtaxireal.databinding.FragmentCarAboutInfoBinding
import info.texnoman.evrtaxireal.di.factory.injectViewModel
import info.texnoman.evrtaxireal.model.request.SetCarAboutRequest
import info.texnoman.evrtaxireal.utils.*
import info.texnoman.evrtaxireal.utils.Extensions.afterTextChanged
import info.texnoman.evrtaxireal.utils.Extensions.validate


class CarAboutInfoFragment : BaseFragment<FragmentCarAboutInfoBinding, UserViewModel>() {
    var danRegionList =
        ArrayList<RegionResponse>()   //-->Viloyat yoki davlatni saqlab turish uchun list
    var gaRegionList = ArrayList<RegionResponse>()
    var carBrandList = ArrayList<CarBrand>()
    var carModelList = ArrayList<CarBrand>()

    var carModelId =0
    lateinit var authViewmodel: AuthViewModel
    override fun setupViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentCarAboutInfoBinding = FragmentCarAboutInfoBinding.inflate(inflater, container, false)

    override fun injectViewModel() {
        mViewModel = injectViewModel(viewModelFactory)

    }

    override fun getViewModelClass(): Class<UserViewModel> = UserViewModel::class.java
    override fun init() {

        getRegion()

        getCarBrand()

        getCarModel()

        getCountry()

        getDistrict()

        sendData()
        //
        binding.apply {
            setOnItemSelectedListener(spinRegion2, spinRegion1, spinCarBrand, spinCarModel)
        }
        typeService()
    }

    private fun sendData() {
        binding.btnSend.setOnClickListener {
            var carNumber =binding.etCarNumber.validate("To'liq yozing") { s -> s.length in 3..30 }
            var request =SetCarAboutRequest(carModelId = carModelId, carNumber = carNumber.toString(), country1 = 1, country2 = 2, type = 1, token = SaveUserInformation.getAuthInfo().token.toString(), licenseNumber = "AB 7897564465")
               viewModel.setCarAboutInfo(request).observe(viewLifecycleOwner){result->
                   when(result.status){
                       Status.SUCCESS->result.data.let{it->
                           Log.e( "sendData: ",it?.data.toString() )
                       }
                       Status.ERROR->{
                       }
                       Status.LOADING->{

                       }
                   }
               }
        }
    }

    private fun getDistrict() {
       viewModel.fungetDistrict(1)
        viewModel.getDistrict.observe(viewLifecycleOwner){data->
            when(data.status){
                Status.SUCCESS->data.data.let{
                     Log.e("tumanlar",data.data?.data.toString())
                }
                Status.LOADING->{

                }
                Status.ERROR->{

                }
            }
        }
    }

    private fun getRegion() {
        viewModel.fungetRegion(1)
        viewModel.getRegion.observe(viewLifecycleOwner) { result ->
            when (result.status) {
                Status.SUCCESS -> result.data.let {
                    if (it != null) {
                        danRegionList.addAll(it.data!!)
                        gaRegionList.addAll(it.data!!)
                        /**
                         * Qayerdan borishini tanlash
                         */
                        binding.spinRegion1.item = getRegionNameList(it.data!!) as List<Any>?
                      binding.spinRegion1.setSelection(0)
                        /**
                         * Qayerga borishini tanlash
                         */
                        binding.spinRegion2.item = getRegionNameList(it.data!!) as List<Any>?
                         binding.spinRegion2.setSelection(0)
                    }
                }

            }
        }
    }

    private fun getCountry(){
         viewModel.getCountryModel()
        viewModel.getCountryModel.observe(viewLifecycleOwner){data->
            when (data.status) {
                Status.SUCCESS-> data.data.let{
                    if (it?.data!=null){
                    binding.spinRegion1.item = getCountryNameList(it?.data!!) as List<Any>?
                    binding.spinRegion1.setSelection(0)
                    binding.spinRegion2.item = getCountryNameList(it?.data!!) as List<Any>?
                    binding.spinRegion2.setSelection(1)
                    }
                }
            }
        }
    }

    private fun getCarBrand() {
        viewModel.getCardBrand()
        viewModel.getCarBrand.observe(viewLifecycleOwner) { status ->
            when (status.status) {
                Status.SUCCESS -> status.data.let {
                    if (it?.data != null) {
                        binding.spinCarBrand.item = getCarModelNameList(it?.data!!) as List<Any>?
                        carBrandList.clear()
                        carBrandList.addAll(it?.data!!)
                    }
                }
                Status.ERROR -> {}
                Status.LOADING -> {

                }
            }
        }
    }

    private fun getCarModel() {
        viewModel.getCarModel.observe(viewLifecycleOwner) { data ->
            when (data.status) {
                Status.SUCCESS -> data.data.let {
                    // Log.e("data",it?.data.toString())
                    if (it?.data != null) {
                        binding.spinCarModel.item = getCarModelNameList(it.data!!) as List<Any>?
                        binding.spinCarModel.setSelection(0)
                        carModelList.clear()
                        carModelList.addAll(it.data!!)
                    }
                }
            }
        }
    }

    private fun typeService() {
        binding.apply {
            radioGroup.setOnCheckedChangeListener { group, checkedId ->
                when (checkedId) {
                    R.id.radioInUzbekistan -> {
                        radioInUzbekistan.changeColor(R.color.colorPrimary)
                        radioNational.changeColor(R.color.radio_flat_text_selector)
                    }
                    R.id.radioNational -> {
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
                        R.id.spinRegion2 -> {

                        }
                        R.id.spinCarBrand -> {

                            viewModel.getCarModel(carBrandList[position].id!!)
                        }
                        R.id.spinCarModel -> {
                             carModelId = carModelList[position].id!!
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