package info.texnoman.evrtaxireal.splash

import android.app.Activity
import android.content.Intent
import android.content.IntentSender
import android.content.res.Configuration
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import info.texnoman.evrtaxireal._driver.main.DriverActivity
import info.texnoman.evrtaxireal._user.main.UserActivity
import info.texnoman.evrtaxireal.base.BaseActivity
import info.texnoman.evrtaxireal.databinding.ActivitySplashBinding
import info.texnoman.evrtaxireal.main.MainViewModel
import info.texnoman.evrtaxireal.utils.PassangerOrDrive
import info.texnoman.evrtaxireal.utils.TypeService
import info.texnoman.evrtaxireal.utils.shared.DrivePassanger

import java.util.*


class SplashActivity : BaseActivity<ActivitySplashBinding,MainViewModel>() {
    private val SPLASH_DISPLAY_LENGTH = 1000L
   /* private lateinit var binding: ActivitySplashBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupView()

    }*/

    override fun injectViewModel() {

    }

    override fun getViewModelClass(): Class<MainViewModel> = MainViewModel::class.java

    override fun init() {
    }

    override fun setupViewBinding(inflater: LayoutInflater): ActivitySplashBinding =
        ActivitySplashBinding.inflate(layoutInflater)


    private fun setLang(language: String) {
        Log.e("language", language.toString())
        val locale = Locale(language)
        Locale.setDefault(locale)
        val config = Configuration()
        config.locale = locale
        baseContext.resources.updateConfiguration(config, baseContext.resources.displayMetrics)
        /* val dm = resources.displayMetrics
             val conf = resources.configuration
             val locale = Locale(language)
             conf.setLocale(locale)
             if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                 this.createConfigurationContext(conf) //for Android 7+
             } else {
                 resources.updateConfiguration(conf, dm) //for Android 6-
             }*/
    }


    private fun setupView() {
        Handler(mainLooper).postDelayed({
            if (DrivePassanger.getTypeService() == PassangerOrDrive.Driver) {
                val mainIntent = Intent(this, DriverActivity::class.java)
                this.startActivity(mainIntent)
                this.finish()
            } else {
                val mainIntent = Intent(this, UserActivity::class.java)
                this.startActivity(mainIntent)
                this.finish()
            }
        }, SPLASH_DISPLAY_LENGTH)
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