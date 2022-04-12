package info.texnoman.evrtaxireal.utils

import info.texnoman.evrtaxireal.auth.model.EntryNumberResponse
import info.texnoman.evrtaxireal.auth.model.SignResponse
import io.paperdb.Paper
object SaveUserInformation {
    val key ="sfekjg"
    val tokenkey ="sffkjfggekjg"
   /* fun saveData(model:registerRequest){
        Paper.book().write(key,model)
    }
    fun getData():registerRequest{
        return Paper.book().read<registerRequest>(key, registerRequest())!!
    }*/

    fun saveAuthInfo(token:EntryNumberResponse){
      Paper.book().write(tokenkey,token)
    }
    fun getAuthInfo(): EntryNumberResponse {
        return Paper.book().read<EntryNumberResponse>(tokenkey,EntryNumberResponse(""))!!
    }
   // SignResponse("","","","","",0,"","","","")
    fun delete(){
        Paper.book().delete(tokenkey)
    }
}