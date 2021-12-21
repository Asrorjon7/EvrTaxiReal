package info.texnoman.evrtaxireal.main

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import info.texnoman.evrtaxireal.R
import info.texnoman.evrtaxireal.base.BaseActivity
import info.texnoman.evrtaxireal.databinding.ActivityMainBinding
import info.texnoman.evrtaxireal.di.factory.injectViewModel
import android.graphics.drawable.ColorDrawable
import info.texnoman.evrtaxireal.utils.SharedViewModel
import info.texnoman.evrtaxireal.utils.hideKeyboard


class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {
    lateinit var sharedViewModel: SharedViewModel

    override fun injectViewModel() {
        mViewModel = injectViewModel(viewModelFactory)
    }

    override fun getViewModelClass(): Class<MainViewModel> = MainViewModel::class.java

    override fun setupViewBinding(inflater: LayoutInflater): ActivityMainBinding {
        return ActivityMainBinding.inflate(inflater)
    }
    override fun init() {
        setSupportActionBar(binding.toolbar)
        binding.toolbar.title = "Salomlar"
        val colorDrawable = ColorDrawable(Color.parseColor("#F9FAFB"))
        supportActionBar?.setBackgroundDrawable(colorDrawable)
        sharedViewModel = injectViewModel(viewModelFactory)
        sharedViewModel.pageTitle.observe(this@MainActivity, {
            binding.toolbar.title = it.toString()
        })
       hideKeyboar()
    }
    private fun hideKeyboar() {
        binding.mainLayout.setOnClickListener{
            it.hideKeyboard()
        }
    }
    fun hideActionBar() {
        supportActionBar?.hide()
    }
    fun showActionBar() {
        supportActionBar?.show()
    }


}