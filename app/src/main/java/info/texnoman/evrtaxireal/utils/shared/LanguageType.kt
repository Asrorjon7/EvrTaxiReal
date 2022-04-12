package info.texnoman.evrtaxireal.utils.shared

import info.texnoman.evrtaxireal.utils.PassangerOrDrive
import io.paperdb.Paper

object LanguageType {


    var key  ="efeqweer"
    fun saveLanguage(language: String, position: Int) {
        Paper.book().write("language", language)
        Paper.book().write("position", position)
    }

    fun getLanguage(): String {
        return Paper.book().read("language", "ru")!!
    }
}