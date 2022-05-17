package info.texnoman.evrtaxireal.splash

import android.app.Activity
import android.content.Intent
import android.content.IntentSender
import android.content.res.Configuration
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import info.texnoman.evrtaxireal.R
import info.texnoman.evrtaxireal._driver.main.DriverActivity
import info.texnoman.evrtaxireal._user.main.UserActivity
import info.texnoman.evrtaxireal.auth.model.ViewPagerModel
import info.texnoman.evrtaxireal.base.BaseActivity
import info.texnoman.evrtaxireal.databinding.ActivitySplashBinding
import info.texnoman.evrtaxireal.main.MainActivity
import info.texnoman.evrtaxireal.main.MainViewModel
import info.texnoman.evrtaxireal.utils.PassangerOrDrive
import info.texnoman.evrtaxireal.utils.TypeService
import info.texnoman.evrtaxireal.utils.gone
import info.texnoman.evrtaxireal.utils.shared.DrivePassanger
import info.texnoman.evrtaxireal.utils.shared.FirstVisit
import info.texnoman.evrtaxireal.utils.visible
import kotlinx.coroutines.Dispatchers

import java.util.*


class SplashActivity : BaseActivity<ActivitySplashBinding, MainViewModel>() {
    private val SPLASH_DISPLAY_LENGTH = 2000L
    var firstVisit: Boolean = false
    override fun injectViewModel() {
    }

    override fun getViewModelClass(): Class<MainViewModel> = MainViewModel::class.java

    override fun init() {
        firstVisit = FirstVisit.getVisit()
        Dispatchers.IO.let {
            val animation = AnimationUtils.loadAnimation(this, R.anim.slide_in_left)
            binding.animationLogo.animation = animation
            setupView()
        }
        var list = arrayListOf<ViewPagerModel>(
            ViewPagerModel(R.drawable.view1, "Siz uchun ishlaymiz", "Shunchaki dasturimizga kiring va o’zingizga ma’qul kelgan narxda mashinaga buyurtma bering!"),
            ViewPagerModel(R.drawable.view2, "Yanada qulay", "Chet elga chiqish uchun mashina izlayapsizmi? Endi bu ham muammo emas! "),
            ViewPagerModel(R.drawable.view3, "Siz kutganingizdan-da tez", "O’z vaqtingizni tejagan xolda, eng yaxshi haydovchilarni tanlash imkoniyatini qo’lga kiriting")
        )
        var adapter = ViewPagerAdapter(this, list = list)
        binding.viewPager.adapter = adapter

        val zoomOutPageTransformer = ZoomOutPageTransformer()
        binding.viewPager.setPageTransformer { page, position ->
            zoomOutPageTransformer.transformPage(page, position)
            Log.e("position",
                toString())
           var item =binding.viewPager.currentItem
            if (item ==2)  binding.action.visible() else binding.action.gone()
        }
        binding.dotsIndicator.setViewPager2(binding.viewPager)
        binding.action.setOnClickListener {
            intent()
        }
    }

    override fun setupViewBinding(inflater: LayoutInflater): ActivitySplashBinding =
        ActivitySplashBinding.inflate(layoutInflater)

    private fun setupView() {
        Handler(mainLooper).postDelayed({
            if (!firstVisit) {
            intent()
            } else {
                binding.apply {
                    animationLogo.gone()
                    ivprovide.gone()
                    dotsIndicator.visible()
                    viewPager.visible()
                }

                FirstVisit.saveVisit(true)
            }
        }, SPLASH_DISPLAY_LENGTH)
    }
    private fun  intent(){
        if (token != "") {
            if (DrivePassanger.getTypeService() == PassangerOrDrive.Driver) {
                val mainIntent = Intent(this, DriverActivity::class.java)
                this.startActivity(mainIntent)
                this.finish()
            } else {
                val mainIntent = Intent(this, UserActivity::class.java)
                this.startActivity(mainIntent)
                this.finish()
            }
        } else {
            val intent = Intent(this, MainActivity::class.java)
            this.startActivity(intent)
        }
    }
    private fun navigateToTechnicalWorks() {
        /*supportFragmentManager.beginTransaction()
            .add(
                binding.splashContainer.id,
                TechnicalWorkInProgressFragment()
            ).commit()*/
    }

    private fun popupSnackbarForCompleteUpdate() {
        /*   val snackbar = Snackbar.make(binding.root,
               "Только что было загружено обновление.",
               Snackbar.LENGTH_INDEFINITE)
           snackbar.setAction("НАЧАТЬ СНАЧАЛА") { appUpdateManager.completeUpdate() }
           snackbar.setActionTextColor(ContextCompat.getColor(this, R.color.yellow))
           snackbar.show()*/
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == APP_UPDATE_REQUEST_CODE) {
            if (resultCode != Activity.RESULT_OK) {
                Toast.makeText(
                    this,
                    "Не удалось обновить приложение. Повторите попытку при следующем запуске приложения.",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
    /* override fun onResume() {
         super.onResume()
         appUpdateManager
             .appUpdateInfo
             .addOnSuccessListener { appUpdateInfo ->

                 // If the update is downloaded but not installed,
                 // notify the user to complete the update.
                 if (appUpdateInfo.installStatus() == InstallStatus.DOWNLOADED) {
                     popupSnackbarForCompleteUpdate()
                 }

                 //Check if Immediate update is required
                 try {
                     if (appUpdateInfo.updateAvailability() == UpdateAvailability.DEVELOPER_TRIGGERED_UPDATE_IN_PROGRESS) {
                         // If an in-app update is already running, resume the update.
                         appUpdateManager.startUpdateFlowForResult(
                             appUpdateInfo,
                             AppUpdateType.IMMEDIATE,
                             this,
                             APP_UPDATE_REQUEST_CODE)
                     }
                 } catch (e: IntentSender.SendIntentException) {
                     e.printStackTrace()
                 }
             }
     }*/

    companion object {
        private const val APP_UPDATE_REQUEST_CODE = 1991
    }


}