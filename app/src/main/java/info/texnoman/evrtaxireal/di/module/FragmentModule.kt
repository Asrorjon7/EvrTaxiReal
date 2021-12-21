package info.texnoman.evrtaxireal.di.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import info.texnoman.evrtaxireal._user.ui.DirectionChoose.DirectionFragment
import info.texnoman.evrtaxireal._user.ui.OrderHistory.OrderHistoryFragment
import info.texnoman.evrtaxireal._user.ui.Profil.ProfilEditFragment
import info.texnoman.evrtaxireal._user.ui.SearchDriver.SearchDirectionFragment
import info.texnoman.evrtaxireal._user.ui.SetOrder.SetOrderFragment
import info.texnoman.evrtaxireal.auth.ui.CarAboutInfo.CarAboutInfoFragment
import info.texnoman.evrtaxireal.auth.ui.ConfirmNumber.ConfirmNumberFragment
import info.texnoman.evrtaxireal.auth.ui.LoginUser.LoginFragment
import info.texnoman.evrtaxireal.auth.ui.RegLogChoose.RegLogChooseFragment
import info.texnoman.evrtaxireal.auth.ui.UserDriverChoose.UserDriverChooseFragment

@Module
abstract class FragmentModule {
    @ContributesAndroidInjector
    abstract fun contributesChooseRoLFragment(): RegLogChooseFragment

    @ContributesAndroidInjector
    abstract fun contributesChooseUoDFragment(): UserDriverChooseFragment

    @ContributesAndroidInjector
    abstract fun contributesLoginFragment(): LoginFragment

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
}