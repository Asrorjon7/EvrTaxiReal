package info.texnoman.evrtaxireal._user.ui.SetOrder

import android.location.Location
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.NumberPicker
import android.widget.Toast
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.chivorn.smartmaterialspinner.SmartMaterialSpinner
import com.google.android.material.bottomsheet.BottomSheetDialog
import info.texnoman.evrtaxireal.R
import info.texnoman.evrtaxireal._user.main.UserActivity
import info.texnoman.evrtaxireal._user.model.SetOrderModel
import info.texnoman.evrtaxireal._user.model.response.RegionResponse
import info.texnoman.evrtaxireal._user.viewmodel.UserViewModel
import info.texnoman.evrtaxireal.base.BaseFragment
import info.texnoman.evrtaxireal.databinding.FragmentSetOrderBinding
import info.texnoman.evrtaxireal.databinding.WalkingTimeBinding
import info.texnoman.evrtaxireal.di.factory.injectViewModel
import info.texnoman.evrtaxireal.utils.*
import info.texnoman.evrtaxireal.utils.numberPicker.PickerAdapter
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


class SetOrderFragment : BaseFragment<FragmentSetOrderBinding, UserViewModel>(),
    SetOrderAdapter.OnItemClickListener {

    var danRegionList = ArrayList<RegionResponse>()   //-->Viloyat yoki davlatni saqlab turish uchun list
    var danDistrictList = ArrayList<RegionResponse>() //-->Tuman yoki Viloyatni saqlab turadi saqlab turish uchun list
    var gaDistrictList = ArrayList<RegionResponse>()
    var gaRegionList = ArrayList<RegionResponse>()
    var  dateList =ArrayList<String>()
    var firstTime:Boolean =true
    val args: SetOrderFragmentArgs by navArgs()
    lateinit  var adapter: PickerAdapter
    enum class DanYoGa {
        Dan, Ga, not
    }
    var danYoGa: DanYoGa = DanYoGa.not
    override fun injectViewModel() {
        mViewModel = injectViewModel(viewModelFactory)
    }
    override fun getViewModelClass(): Class<UserViewModel> = UserViewModel::class.java
    override fun init() {
        dateList.addAll(getWeekDay())
        requireView().hideKeyboard()
        firstTime =true
       var myLocation =MyLocation()
        NumberPicker(binding)
        checkUzbOrWorld(args)
        typeService()
        searchButton()
        getRegion()
        val locale = context?.resources?.configuration?.locale?.country
         Log.e("locallar",locale.toString())
        binding.apply {
            setOnItemSelectedListener(danRegion, danDistrict, gaRegion, gaDistrict)
        }
        binding.btnChooseTime.setOnClickListener { ///Ketish vaqtini tanlash
            chooseTime()
            Log.e("dateTime", getWeekDay().toString())

        }

        binding.ivGetAddress.setOnClickListener {
        var result=myLocation.getLocation(requireContext(),locationResult)
          if (!result){
              Toast.makeText(requireContext(), getString(R.string.gpsniyoqing), Toast.LENGTH_SHORT).show()
          }
        }
    }
    private fun chooseTime() {

        val dialog =BottomSheetDialog(requireContext(),R.style.BottomSheetDialog)
        val dialogbinding =WalkingTimeBinding.inflate(layoutInflater)
        dialogbinding.apply {
            npDate.minValue=0
            npDate.maxValue=getWeekDay().size-1
            npDate.setFormatter(object :NumberPicker.Formatter{
                override fun format(value: Int): String {

                    return dateList[value]
                }
            })

            npDate.displayedValues = getWeekDay().toTypedArray()
        }
        dialogbinding.btn.setOnClickListener {
            Toast.makeText(requireContext(), "bosildi", Toast.LENGTH_SHORT).show()
        }
        dialog.setContentView(dialogbinding.root)
        dialog.show()
    }
    /**
     * Viloyatlarni olish uchun function
     */

    private  fun getWeekDay(): MutableList<String> {
        val c1: Calendar = Calendar.getInstance()

        val dates: MutableList<String> = ArrayList()

        val dateFormat: DateFormat = SimpleDateFormat("dd   MMMM   yyyy")
        dates.add(dateFormat.format(c1.time))
        for (i in 0..30) {
            c1.add(Calendar.DATE, 1)
            dates.add(dateFormat.format(c1.time))
        }
        return dates
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
                    binding.danRegion.item = getRegionNameList(it.data!!) as List<Any>?
                    binding.danRegion.setSelection(11)
                    /**
                     * Qayerga borishini tanlash
                     */
                    binding.gaRegion.item = getRegionNameList(it.data!!) as List<Any>?
                    binding.gaRegion.setSelection(11)

                }
                Status.LOADING -> {
                }
                Status.ERROR -> {
                }
            }
        }
    }

    fun getDistrict(id: Int) {
        viewModel.fungetDistrict(token, id)
        viewModel.getDistrict.observe(viewLifecycleOwner) { result ->
            when (result.status) {
                Status.SUCCESS -> result.data.let {
                    danDistrictList.addAll(it?.data!!)
                    gaDistrictList.addAll(it.data!!)
                    if (danYoGa == DanYoGa.Dan) {
                        binding.apply {
                            danDistrict.item = getRegionNameList(it.data!!) as List<Any>?
                            danDistrict.setSelection(0)
                            loadingView.gone()
                            danDistrict.isEnabled = true
                        }
                    }
                    if (danYoGa == DanYoGa.Ga) {
                        binding.apply {
                            gaDistrict.item = getRegionNameList(it.data!!) as List<Any>?
                            gaDistrict.setSelection(0)
                            loadingView1.gone()
                            gaDistrict.isEnabled = true
                        }
                    }
                    if (firstTime){
                        binding.apply {
                        danDistrict.item = getRegionNameList(it.data!!) as List<Any>?
                        danDistrict.setSelection(0)
                        loadingView.gone()
                        danDistrict.isEnabled = true
                            firstTime=false
                        }
                    }
                    Log.e("danDistrict",binding.danDistrict.count.toString())
                    Log.e("gaDistrict",binding.gaDistrict.count.toString())

                }
                Status.LOADING -> {

                    binding.gaDistrict.isEnabled = false
                    binding.danDistrict.isEnabled = false
                }
                Status.ERROR -> {

                    binding.loadingView.gone()
                }
            }
        }
    }

    private fun searchButton() {
        binding.btnSearch.setOnClickListener {
            Navigation.findNavController(it)
                .navigate(R.id.action_setOrderFragment_to_searchDirectionFragment)
        }
    }

    var locationResult: MyLocation.LocationResult = object : MyLocation.LocationResult() {
        override fun gotLocation(location: Location) {
            // TODO Auto-generated method stub
            val Longitude: Double = location.longitude
            val Latitude: Double = location.latitude
            Log.e("long", "$Longitude $Latitude")

        }
    }



    private fun typeService() {
        binding.radioGroup.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.radioTypePost -> {
                    mViewModel.setTypeService(TypeService.Post)
                }
                R.id.radioTypePassanger -> {
                    mViewModel.setTypeService(TypeService.Passanger)
                }
            }
        }
        mViewModel.typeService.observe(viewLifecycleOwner) { type ->
            if (type == TypeService.Passanger) {
                binding.apply {
                    lvPassangerCount.visible()
                 //   lvPostvisible.gone()
                }
            } else {
                binding.apply {
                    lvPassangerCount.gone()
                //    lvPostvisible.visible()
                }

            }

        }
    }

    private fun checkUzbOrWorld(args: SetOrderFragmentArgs) {
        if (args.checkworlduzb == CheckUzbOrWorld.WORLD) {
            binding.apply {
                etDanState.visible()
                etGaState.visible()
            }
        } else {
            binding.apply {
                etDanState.gone()
                etGaState.gone()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()

        (requireActivity() as UserActivity).navigationIcon()
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
                        R.id.danRegion -> {
                            binding.loadingView.visible()
                            danYoGa = DanYoGa.Dan
                            getDistrict(getRegionId(danRegionList, spinner.selectedItemPosition))
                        }
                        R.id.danDistrict -> {

                        }
                        R.id.gaRegion -> {
                            binding.loadingView1.visible()
                            danYoGa = DanYoGa.Ga
                            getDistrict(getRegionId(gaRegionList, spinner.selectedItemPosition))
                        }
                        R.id.gaDistrict -> {
                        }
                    }

                }
                override fun onNothingSelected(adapterView: AdapterView<*>?) {
                    spinner.errorText = "On Nothing Selected"
                }
            }
        }
    }

    override fun setupViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentSetOrderBinding = FragmentSetOrderBinding.inflate(inflater, container, false)

    override fun SetOrderClickListener(model: SetOrderModel) {

    }

}