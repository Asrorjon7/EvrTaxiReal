package info.texnoman.evrtaxireal._driver.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import info.texnoman.evrtaxireal.R
import info.texnoman.evrtaxireal._driver.viewmodel.DriverViewmodel
import info.texnoman.evrtaxireal.base.BaseFragment
import info.texnoman.evrtaxireal.databinding.FragmentPaymentBinding


class PaymentFragment : BaseFragment<FragmentPaymentBinding,DriverViewmodel>() {

    override fun injectViewModel() {
      }
    override fun getViewModelClass(): Class<DriverViewmodel> =DriverViewmodel::class.java

    override fun init() {
        payme()
        click()
    }

    private fun payme() {
        binding.lvClick.setOnClickListener {
        backgroundClick()
        }

    }
    private fun click() {
      binding.lvPayme.setOnClickListener {
      backgroundPayme()
      }
    }

    override fun setupViewBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentPaymentBinding =
        FragmentPaymentBinding.inflate(layoutInflater,container,false)


    fun backgroundPayme(){
        binding.lvPayme.background = ContextCompat.getDrawable(requireContext(),R.drawable.payment_stroke_color_green)
        binding.lvClick.background = ContextCompat.getDrawable(requireContext(),R.drawable.payment_back_grey)
    }
    fun backgroundClick(){
        binding.lvPayme.background = ContextCompat.getDrawable(requireContext(),R.drawable.payment_back_grey)
        binding.lvClick.background = ContextCompat.getDrawable(requireContext(),R.drawable.payment_stroke_color_green)

    }

}