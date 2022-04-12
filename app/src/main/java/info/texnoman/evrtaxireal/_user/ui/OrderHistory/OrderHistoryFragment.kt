package info.texnoman.evrtaxireal._user.ui.OrderHistory

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import info.texnoman.evrtaxireal._user.model.OrderHistoryModel
import info.texnoman.evrtaxireal._user.viewmodel.UserViewModel
import info.texnoman.evrtaxireal.base.BaseFragment
import info.texnoman.evrtaxireal.databinding.FragmentOrderHistoryBinding
import info.texnoman.evrtaxireal.di.factory.injectViewModel
class OrderHistoryFragment : BaseFragment<FragmentOrderHistoryBinding,UserViewModel>() {
   /* override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_order_history, container, false)
    }*/
    override fun injectViewModel() {
        mViewModel =injectViewModel(viewModelFactory)
    }
    override fun getViewModelClass(): Class<UserViewModel>  =UserViewModel::class.java

    override fun init() {

        loadFake()
    }

    private fun loadFake() {
        var list= ArrayList<OrderHistoryModel>()
        for ( i in  0..10){
            list.add(OrderHistoryModel(title = "title"))
        }
        binding.recyclerView.apply {
            setHasFixedSize(true)
            layoutManager =LinearLayoutManager(requireContext())
            adapter = OrderHistoryAdapter(requireContext(), list)
        }
    }

    override fun setupViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentOrderHistoryBinding = FragmentOrderHistoryBinding.inflate(inflater,container,false)

}