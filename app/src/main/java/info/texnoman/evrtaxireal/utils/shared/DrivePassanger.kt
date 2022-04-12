package info.texnoman.evrtaxireal.utils.shared

import info.texnoman.evrtaxireal.utils.CheckUzbOrWorld
import info.texnoman.evrtaxireal.utils.PassangerOrDrive
import info.texnoman.evrtaxireal.utils.TypeService
import io.paperdb.Paper

object DrivePassanger {
    var key  ="efer"
    fun saveTypeService(type: PassangerOrDrive){
        Paper.book().write(key,type)
    }
    fun getTypeService(): PassangerOrDrive {
        return Paper.book().read<PassangerOrDrive>(key, PassangerOrDrive.Default)!!
    }
}