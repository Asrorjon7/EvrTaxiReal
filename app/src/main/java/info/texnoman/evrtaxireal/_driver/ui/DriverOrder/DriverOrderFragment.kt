package info.texnoman.evrtaxireal._driver.ui.DriverOrder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import info.texnoman.evrtaxireal.R
import info.texnoman.evrtaxireal._driver.model.DriverOrderModel
import info.texnoman.evrtaxireal._driver.viewmodel.DriverViewmodel
import info.texnoman.evrtaxireal.base.BaseFragment
import info.texnoman.evrtaxireal.databinding.FragmentDriverOrderBinding
import info.texnoman.evrtaxireal.di.factory.injectViewModel

class DriverOrderFragment : BaseFragment<FragmentDriverOrderBinding,DriverViewmodel>() ,DriverOrderAdapter.ItemClickListener{
 //  lateinit var adapter:DriverOrderAdapter
    override fun injectViewModel() {
        mViewModel =injectViewModel(viewModelFactory)
    }

    override fun getViewModelClass(): Class<DriverViewmodel> = DriverViewmodel::class.java

    override fun init() {
        setUpUI()
    }
    fun setUpUI(){
       // adapter = DriverOrderAdapter(requireContext(), arrayListOf(),this@DriverOrderFragment)

        binding.apply {
            recyclerView.layoutManager =LinearLayoutManager(requireContext())
            recyclerView.setHasFixedSize(true)
            var list :ArrayList<DriverOrderModel> = ArrayList()
            list.add(DriverOrderModel("N 132"))
            list.add(DriverOrderModel("N 132"))
            list.add(DriverOrderModel("N 132"))
            recyclerView.adapter =DriverOrderAdapter(requireContext(),list,this@DriverOrderFragment)

        }
    }
    override fun setupViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentDriverOrderBinding = FragmentDriverOrderBinding.inflate(inflater,container,false)

    override fun OnClickListener(model: DriverOrderModel) {
         Navigation.findNavController(requireView()).navigate(R.id.action_driverOrderFragment_to_driverOrderDetailFragment)
    }


}