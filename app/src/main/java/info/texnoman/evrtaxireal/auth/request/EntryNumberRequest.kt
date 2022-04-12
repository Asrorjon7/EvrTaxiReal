package info.texnoman.evrtaxireal.auth.request

import com.google.gson.annotations.SerializedName

data class EntryNumberRequest(
    @field:SerializedName("phone")
    val phone: String? = null
)
