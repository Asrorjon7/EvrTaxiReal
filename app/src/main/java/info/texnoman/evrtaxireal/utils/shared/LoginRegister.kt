package info.texnoman.evrtaxireal.utils.shared

import info.texnoman.evrtaxireal.utils.RegorLog
import io.paperdb.Paper

object LoginRegister {
    var key  ="registerlar"
    fun saveRegLog(type: RegorLog){
        Paper.book().write(key,type)
    }

    fun getRegLog(): RegorLog {
        return Paper.book().read<RegorLog>(key, RegorLog.Default)!!
    }
}