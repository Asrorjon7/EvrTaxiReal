package info.texnoman.evrtaxireal.auth.model

import com.google.gson.annotations.SerializedName

data class EntryNumberResponse(
	@field:SerializedName("token")
	val token: String? = null
)
