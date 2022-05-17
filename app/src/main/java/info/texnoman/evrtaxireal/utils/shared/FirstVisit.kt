package info.texnoman.evrtaxireal.utils.shared

import io.paperdb.Paper

object FirstVisit {


    var key  ="ffdsdrdfegrg"
    fun saveVisit(visit:Boolean) {
        Paper.book().write("visit", visit)
    }
    fun getVisit(): Boolean {
        return Paper.book().read("visit", false)!!
    }
}