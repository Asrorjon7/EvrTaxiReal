package info.texnoman.evrtaxireal._user.ui.OrderHistoryDetails

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import info.texnoman.evrtaxireal.R
import info.texnoman.evrtaxireal._user.viewmodel.UserViewModel
import info.texnoman.evrtaxireal.base.BaseFragment
import info.texnoman.evrtaxireal.databinding.FragmentHisDetailsBinding
import info.texnoman.evrtaxireal.di.factory.injectViewModel
import info.texnoman.evrtaxireal.utils.Extensions.alert

class OrderHistoryDetailsFragment : BaseFragment<FragmentHisDetailsBinding, UserViewModel>() {
    override fun injectViewModel() {
        mViewModel = injectViewModel(viewModelFactory)
    }
    override fun getViewModelClass(): Class<UserViewModel> = UserViewModel::class.java
    override fun init() {
        binding.btnCancelOrder.setOnClickListener {
            alert(getString(R.string.buyurtma_bekor)) {
                positiveButton(getString(R.string.yoq)) {

                }
                negativeButton(getString(R.string.xa)) {
                    Navigation.findNavController(requireView()).popBackStack()
                }
            }.show()
        }
    }
    override fun setupViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentHisDetailsBinding {
        return FragmentHisDetailsBinding.inflate(inflater, container, false)

    }

}