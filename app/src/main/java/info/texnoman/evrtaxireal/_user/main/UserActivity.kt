package info.texnoman.evrtaxireal._user.main
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.view.GravityCompat
import androidx.navigation.NavController
import info.texnoman.evrtaxireal.R
import info.texnoman.evrtaxireal._user.viewmodel.UserViewModel
import info.texnoman.evrtaxireal.base.BaseActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.navigation.NavigationView
import info.texnoman.evrtaxireal._user.model.NavigationModel
import info.texnoman.evrtaxireal.databinding.ActivityUserBinding
import info.texnoman.evrtaxireal.di.factory.injectViewModel
import info.texnoman.evrtaxireal.utils.gone
import info.texnoman.evrtaxireal.utils.visible
class UserActivity : BaseActivity<ActivityUserBinding, UserViewModel>(),
    NavigationView.OnNavigationItemSelectedListener {

    private lateinit var navController: NavController

    lateinit var appBarConfiguration: AppBarConfiguration

    override fun injectViewModel() {
        mViewModel = injectViewModel(viewModelFactory)
    }

    override fun getViewModelClass(): Class<UserViewModel> = UserViewModel::class.java
    override fun init() {
        loadList()

        initViews()

        setProfile()
    }
    private fun initViews() {
        binding.apply {
            setSupportActionBar(toolbar)
            val toggle = ActionBarDrawerToggle(this@UserActivity, drawerLayout, toolbar, R.string.open, R.string.close)
            drawerLayout.addDrawerListener(toggle)
            toggle.syncState()
            navController = findNavController(R.id.navhost)
            appBarConfiguration = AppBarConfiguration(setOf(R.id.directionFragment), drawerLayout)
            toolbar.setupWithNavController(navController, appBarConfiguration)
            navView.setupWithNavController(navController)
            toolbar.navigationIcon = ContextCompat.getDrawable(this@UserActivity, R.drawable.navigation_icon)
        }
    }
    private fun setProfile() {
        var view = binding.navView.getHeaderView(0);
        var profil = view.findViewById<ConstraintLayout>(R.id.lvProfil)
        profil.setOnClickListener {
            navController.navigate(R.id.profilEditFragment)

            binding.drawerLayout.closeDrawer(GravityCompat.START)
        }
    }

    override fun setupViewBinding(inflater: LayoutInflater): ActivityUserBinding {
        return ActivityUserBinding.inflate(inflater)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        binding.drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    private fun loadList() {
        mViewModel.setNavigationData()
        mViewModel.navigationData.observe(this, { list ->
            binding.recItem.apply {
                setHasFixedSize(true)
                layoutManager = LinearLayoutManager(this@UserActivity)
                adapter = NavigationAdapter(this@UserActivity, list,
                    object : NavigationAdapter.OnItemClickListener {
                        override fun NavigationClick(model: NavigationModel) {
                         //   binding.drawerLayout.closeDrawer(GravityCompat.START)
                           when(model.id){
                           2-> {
                               Log.e("nimagap",id.toString())
                               navController.navigate(R.id.orderHistoryFragment)}


                           }
                            binding.drawerLayout.closeDrawer(GravityCompat.START)
                        }
                    })
            }
        })
    }

    fun navigationIcon() {
        binding.toolbar.navigationIcon =
            ContextCompat.getDrawable(this@UserActivity, R.drawable.navigation_icon)
    }

    fun hideActionBar() {
        binding.appBar.gone()
    }

    fun showActionBar() {
        binding.appBar.visible()
    }


}