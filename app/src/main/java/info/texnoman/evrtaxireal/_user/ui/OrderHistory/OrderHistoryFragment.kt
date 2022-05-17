package info.texnoman.evrtaxireal._user.ui.OrderHistory

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import info.texnoman.evrtaxireal.R
import info.texnoman.evrtaxireal.model.OrderHistoryModel
import info.texnoman.evrtaxireal._user.viewmodel.UserViewModel
import info.texnoman.evrtaxireal.base.BaseFragment
import info.texnoman.evrtaxireal.databinding.FragmentOrderHistoryBinding
import info.texnoman.evrtaxireal.di.factory.injectViewModel
class OrderHistoryFragment : BaseFragment<FragmentOrderHistoryBinding,UserViewModel>(),OrderHistoryAdapter.OnItemClickListener {

    override fun injectViewModel() {
        mViewModel =injectViewModel(viewModelFactory)
    }
    override fun getViewModelClass(): Class<UserViewModel> =UserViewModel::class.java

    override fun init() {
        loadFake()
        binding.apply {
            cvAll.strokeColor=ContextCompat.getColor(requireContext(),R.color.cvAllStroke)
            cvCompleted.strokeColor=ContextCompat.getColor(requireContext(), R.color.cvCompletedStrokeColor)
            cvCanceled.strokeColor=ContextCompat.getColor(requireContext(),R.color.cvCanceledStrokeColor) }
        cvFilter()
    }
    private fun cvFilter() {
        binding.cvAll.setOnClickListener {
         /*   binding.apply {
            cvAll.strokeColor=ContextCompat.getColor(requireContext(),R.color.cvAllStroke)
            cvCompleted.strokeColor=ContextCompat.getColor(requireContext(), R.color.transparent)
            cvCanceled.strokeColor=ContextCompat.getColor(requireContext(),R.color.transparent)
            }*/
        }
        binding.cvCanceled.setOnClickListener {
          /*  binding.apply {
                //cvAll.strokeColorStateList = ColorStateList(Color.parseColor("dfdf"))
                cvCompleted.strokeColor=ContextCompat.getColor(requireContext(), R.color.white)
                cvCanceled.strokeColor=ContextCompat.getColor(requireContext(),R.color.cvCanceledStrokeColor)

            }*/
        }
        binding.cvCompleted.setOnClickListener {
          /*  binding.apply {
                cvAll.strokeColor=ContextCompat.getColor(requireContext(),R.color.transparent)
                cvCompleted.strokeColor=ContextCompat.getColor(requireContext(), R.color.cvCompletedStrokeColor)
                cvCanceled.strokeColor=ContextCompat.getColor(requireContext(),R.color.transparent)

            }*/
        }
    }

    private fun loadFake() {
       var list= ArrayList<OrderHistoryModel>()
        for ( i in  0..10){
            list.add(OrderHistoryModel(title = "title"))
        }
        binding.recyclerView.apply {
            setHasFixedSize(true)
            layoutManager =LinearLayoutManager(requireContext())
            adapter = OrderHistoryAdapter(requireContext(), list,this@OrderHistoryFragment)
        }
    }
    override fun setupViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentOrderHistoryBinding = FragmentOrderHistoryBinding.inflate(inflater,container,false)

    override fun clickListener(model: OrderHistoryModel) {
         Navigation.findNavController(requireView()).navigate(R.id.orderHistoryToDetails)
    }

}