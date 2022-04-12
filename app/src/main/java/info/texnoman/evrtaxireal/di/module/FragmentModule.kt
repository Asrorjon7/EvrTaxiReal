package info.texnoman.evrtaxireal.di.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import info.texnoman.evrtaxireal._driver.ui.DriverOrder.DriverOrderFragment
import info.texnoman.evrtaxireal._driver.ui.DriverOrderDetail.DriverOrderDetailFragment
import info.texnoman.evrtaxireal._user.ui.DirectionChoose.DirectionFragment
import info.texnoman.evrtaxireal._user.ui.OrderHistory.OrderHistoryFragment
import info.texnoman.evrtaxireal._user.ui.Profil.ProfilEditFragment
import info.texnoman.evrtaxireal._user.ui.SearchDriver.SearchDirectionFragment
import info.texnoman.evrtaxireal._user.ui.SetOrder.SetOrderFragment
import info.texnoman.evrtaxireal.auth.ui.CarAboutInfo.CarAboutInfoFragment
import info.texnoman.evrtaxireal.auth.ui.ChooseLanguage.ChooseLanguageFragment
import info.texnoman.evrtaxireal.auth.ui.ConfirmNumber.ConfirmNumberFragment
import info.texnoman.evrtaxireal.auth.ui.LoginUser.LoginAdditionFragment
import info.texnoman.evrtaxireal.auth.ui.LoginUser.RealLoginFragment
import info.texnoman.evrtaxireal.auth.ui.RegLogChoose.EntryFragment
import info.texnoman.evrtaxireal.auth.ui.RegLogChoose.TermsFragment
import info.texnoman.evrtaxireal.auth.ui.UserDriverChoose.UserDriverChooseFragment

@Module
abstract class FragmentModule {

    @ContributesAndroidInjector
    abstract fun  contributrDriveOrderFragment(): DriverOrderFragment

    @ContributesAndroidInjector
    abstract fun contributesChooseRoLFragment(): EntryFragment

    @ContributesAndroidInjector
    abstract fun contributesChooseUoDFragment(): UserDriverChooseFragment

    @ContributesAndroidInjector
    abstract fun contributesLoginFragment(): LoginAdditionFragment

    @ContributesAndroidInjector
    abstract fun contributesConfirmFragment(): ConfirmNumberFragment

    @ContributesAndroidInjector
    abstract fun contributesCarInfoAboutFragment(): CarAboutInfoFragment

    @ContributesAndroidInjector
    abstract fun contributesDirectionFragment(): DirectionFragment

    @ContributesAndroidInjector
    abstract fun contributesSetOrderFragment(): SetOrderFragment

    @ContributesAndroidInjector
    abstract fun contributerSetMapFragment():SearchDirectionFragment

    @ContributesAndroidInjector
    abstract fun  contributerSetProfilEditFragment():ProfilEditFragment

    @ContributesAndroidInjector
    abstract fun  contributerSetOrderEditFragment():OrderHistoryFragment

    @ContributesAndroidInjector
    abstract fun  contributerDriverOrderDetail():DriverOrderDetailFragment


    @ContributesAndroidInjector
    abstract fun contributerLoginRealFragment():RealLoginFragment

    @ContributesAndroidInjector
    abstract fun contributerTermsUseFragment():TermsFragment

    @ContributesAndroidInjector
    abstract fun contributerChooseLanguageFragment():ChooseLanguageFragment


}