package info.texnoman.evrtaxireal.auth.ui.RegLogChoose

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.navigation.Navigation
import info.texnoman.evrtaxireal.R
import info.texnoman.evrtaxireal.auth.ui.viewmodel.AuthViewModel
import info.texnoman.evrtaxireal.base.BaseFragment
import info.texnoman.evrtaxireal.databinding.FragmentTermsBinding
import info.texnoman.evrtaxireal.di.factory.injectViewModel


class TermsFragment : BaseFragment<FragmentTermsBinding,AuthViewModel>() {
  /*  override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_terms, container, false)
    }
*/
    override fun injectViewModel() {
        mViewModel =injectViewModel(viewModelFactory)
    }

    override fun getViewModelClass(): Class<AuthViewModel> =AuthViewModel::class.java
    override fun init() {
        val img = ContextCompat.getDrawable(requireContext(),R.drawable.back)
        img?.setBounds(60, 0, 0,0 )
        binding.btnBack.setCompoundDrawables(img, null, null, null)
        binding.btnBack.setOnClickListener {
            Navigation.findNavController(it).popBackStack()
        }
    }

    override fun setupViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentTermsBinding = FragmentTermsBinding.inflate(inflater,container,false)


}