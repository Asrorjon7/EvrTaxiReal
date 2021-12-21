package info.texnoman.evrtaxireal._user.ui.SetOrder

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import info.texnoman.evrtaxireal.R
import info.texnoman.evrtaxireal._user.main.UserActivity
import info.texnoman.evrtaxireal._user.model.SetOrderModel
import info.texnoman.evrtaxireal._user.viewmodel.UserViewModel
import info.texnoman.evrtaxireal.base.BaseFragment
import info.texnoman.evrtaxireal.databinding.FragmentSetOrderBinding
import info.texnoman.evrtaxireal.di.factory.injectViewModel
import info.texnoman.evrtaxireal.utils.CheckUzbOrWorld
import info.texnoman.evrtaxireal.utils.TypeService
import info.texnoman.evrtaxireal.utils.gone
import info.texnoman.evrtaxireal.utils.visible
import java.util.*
import kotlin.collections.ArrayList
class SetOrderFragment : BaseFragment<FragmentSetOrderBinding, UserViewModel>(),
    SetOrderAdapter.OnItemClickListener {
    val args: SetOrderFragmentArgs by navArgs()

    override fun injectViewModel() {
        mViewModel = injectViewModel(viewModelFactory)
    }
    override fun getViewModelClass(): Class<UserViewModel> = UserViewModel::class.java
    override fun init() {

        setupUI()

        checkUzbOrWorld(args)

        typeService()

        postType()

        countPassanger()

        searchButton()

        val items = listOf("Material", "Design", "Components", "Android","Material", "Design", "Components", "Android","Material", "Design", "Components", "Android","Material", "Design", "Components", "Android","Material", "Design", "Components", "Android","Material", "Design", "Components", "Android")
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_dropdown_item_1line, items)
        (binding.autocompleteText)?.setAdapter(adapter)
    }

    private fun searchButton() {
        binding.btnSearch.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_setOrderFragment_to_searchDirectionFragment)
        }
    }

    private fun countPassanger() {
       binding.apply {
           val count = resources.getStringArray(R.array.passangercount)
           countPassanger.maxValue =count.size-1
           countPassanger.minValue =0
           countPassanger.wrapSelectorWheel = false
           countPassanger.displayedValues = count
           countPassanger.setOnValueChangedListener { _, _, newVal ->
               Log.e("tanlangani","${count[newVal]}")
           }
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
        mViewModel.typeService.observe(viewLifecycleOwner, {type->
           if (type ==TypeService.Passanger){
            binding.apply {
                lvPassangerCount.visible()
                lvPostvisible.gone()
            }
           }else{
               binding.apply {
                   lvPassangerCount.gone()
                   lvPostvisible.visible()
               }

            }

        })
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
    private fun setupUI() {
        binding.apply {
            recyclerview.apply {
                layoutManager =
                    LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
                setHasFixedSize(true)
                var list: ArrayList<SetOrderModel> = ArrayList()
                list.add(SetOrderModel(1, "Darhol"))
                list.add(SetOrderModel(2, "1"))
                list.add(SetOrderModel(3, "3"))
                list.add(SetOrderModel(4, "5"))
                list.add(SetOrderModel(5, "7"))
                list.add(SetOrderModel(6, "10"))
                list.add(SetOrderModel(7, "12"))
                adapter = SetOrderAdapter(requireContext(), list, this@SetOrderFragment)

            }

        }
    }

    private fun postType(){
        binding.apply {
            rvPostType.apply {
                 layoutManager =LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
                 setHasFixedSize(true)
                var list: ArrayList<SetOrderModel> = ArrayList()
                list.add(SetOrderModel(1, "Izohda yozaman "))
                list.add(SetOrderModel(2, "Dokument"))
                list.add(SetOrderModel(3, "Pul"))
                list.add(SetOrderModel(4, "Buyum"))

                adapter = SetOrderAdapter(requireContext(), list, this@SetOrderFragment)

            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        (requireActivity() as UserActivity).navigationIcon()
    }
   /* private fun setPassangerCount() {
        val count = resources.getStringArray(R.array.passangercount)
        val adapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_item, count
        )
        binding.spinner.adapter = adapter
    }*/

    override fun setupViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentSetOrderBinding = FragmentSetOrderBinding.inflate(inflater, container, false)

    override fun SetOrderClickListener(model: SetOrderModel) {

    }

}