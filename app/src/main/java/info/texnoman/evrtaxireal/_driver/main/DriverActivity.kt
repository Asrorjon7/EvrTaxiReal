package info.texnoman.evrtaxireal._driver.main

import android.content.Intent
import android.view.LayoutInflater
import android.widget.Button
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.view.GravityCompat
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import info.texnoman.evrtaxireal.R
import info.texnoman.evrtaxireal._user.main.NavigationAdapter
import info.texnoman.evrtaxireal._user.main.UserActivity
import info.texnoman.evrtaxireal.model.NavigationModel
import info.texnoman.evrtaxireal._user.viewmodel.UserViewModel
import info.texnoman.evrtaxireal.base.BaseActivity
import info.texnoman.evrtaxireal.databinding.ActivityDriverBinding
import info.texnoman.evrtaxireal.di.factory.injectViewModel
import info.texnoman.evrtaxireal.utils.PassangerOrDrive
import info.texnoman.evrtaxireal.utils.gone
import info.texnoman.evrtaxireal.utils.shared.DrivePassanger
import info.texnoman.evrtaxireal.utils.visible

class DriverActivity : BaseActivity<ActivityDriverBinding, UserViewModel>() {
    override fun injectViewModel() {
        mViewModel = injectViewModel(viewModelFactory)
    }

    private lateinit var navController: NavController
    lateinit var appBarConfiguration: AppBarConfiguration
    override fun getViewModelClass(): Class<UserViewModel> = UserViewModel::class.java

    override fun init() {
        initViews()
        setProfile()
        loadList()

    }

    private fun initViews() {
        var driver = findViewById<Button>(R.id.btnPassanger)
        driver.setOnClickListener {
            var intent = Intent(this@DriverActivity, UserActivity::class.java)
            DrivePassanger.saveTypeService(PassangerOrDrive.Passanger)
            startActivity(intent)
            finish()
        }
        binding.apply {
            setSupportActionBar(toolbar)
            val toggle = ActionBarDrawerToggle(this@DriverActivity, drawerLayout, toolbar, R.string.open, R.string.close)
            drawerLayout.addDrawerListener(toggle)
            toggle.syncState()
            navController = findNavController(R.id.navhost)
            appBarConfiguration = AppBarConfiguration(setOf(R.id.directionFragment), drawerLayout)
            toolbar.setupWithNavController(navController, appBarConfiguration)
            navView.setupWithNavController(navController)
            toolbar.navigationIcon =
                ContextCompat.getDrawable(this@DriverActivity, R.drawable.navigation_icon)
        }
    }

    private fun setProfile() {
        var view = binding.navView.getHeaderView(0);
        var profil = view.findViewById<ConstraintLayout>(R.id.lvProfil)
        /* profil.setOnClickListener {
             navController.navigate(R.id.paymentFragment)
             binding.drawerLayout.closeDrawer(GravityCompat.START)
         }*/
    }

    private fun loadList() {
        mViewModel.setDriverData()
        mViewModel.navigationDriverData.observe(this) { list ->
            binding.recItem.apply {
                setHasFixedSize(true)
                layoutManager = LinearLayoutManager(this@DriverActivity)
                adapter = NavigationAdapter(this@DriverActivity, list,
                    object : NavigationAdapter.OnItemClickListener {
                        override fun NavigationClick(model: NavigationModel) {
                            //   binding.drawerLayout.closeDrawer(GravityCompat.START)
                            when (model.id) {
                                1 -> {
                                    navController.navigate(R.id.paymentFragment)
                                }
                                3 -> {
                                    //Log.e("salom",model.id.toString())
                                     navController.navigate(R.id.orderForDriverHistoryFragment)
                                }

                            }
                            binding.drawerLayout.closeDrawer(GravityCompat.START)
                        }
                    })
            }
        }
    }

    fun navigationIcon() {

        binding.toolbar.navigationIcon =
            ContextCompat.getDrawable(this@DriverActivity, R.drawable.navigation_icon)
    }

    fun hideActionBar() {
        binding.appBar.gone()
    }

    fun showActionBar() {
        binding.appBar.visible()
    }

    /* fun offerShow(){
         binding.lvOffer.apply {
             /* pivotX = width.toFloat()
              translationXSpring(targetValue = screenSize - width.toFloat())
                  .force(stiffness = 1000f)
                  .scaleXSpring(targetValue = 0.6f)
                  .lastForce(stiffness = 50f)
                  .after(
                      scaleXSpring(targetValue = 1f)
                          .after(
                              translationXSpring(targetValue = screenSize * 0.2f)
                                  .force(dampingRatio = 0.3f)
                          )
                  )
                  .start()*/
             scaleXSpring(targetValue = 0.6f)
                 .scaleYSpring(targetValue = 0.6f)
                 .after(
                     scaleXSpring(targetValue = 1f)
                         .scaleYSpring(
                             targetValue = 1f,
                         )
                         .force(dampingRatio = 0.15f)
                 )
                 .start()}
         binding.btnCancelOffer.setOnClickListener {
             binding.lvOffer.gone()
         }
         binding.lvOffer.visible()
     }*/
    override fun setupViewBinding(inflater: LayoutInflater): ActivityDriverBinding =
        ActivityDriverBinding.inflate(layoutInflater)
}