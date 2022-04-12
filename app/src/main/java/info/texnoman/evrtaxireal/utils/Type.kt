
   
package info.texnoman.evrtaxireal.utils

import info.texnoman.evrtaxireal.utils.CheckUzbOrWorld
import io.paperdb.Paper

object Type {
    var key  ="efkegoner"
    fun SaveType(type:CheckUzbOrWorld){
        Paper.book().write(key,type)
    }
    fun getType(): CheckUzbOrWorld {
        return Paper.book().read<CheckUzbOrWorld>(key, CheckUzbOrWorld.Default)!!
    }
}