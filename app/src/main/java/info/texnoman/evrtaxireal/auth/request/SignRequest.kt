package info.texnoman.evrtaxireal.auth.request

import com.google.gson.annotations.SerializedName

data class SignRequest(

	@field:SerializedName("sex")
	val sex: Int? = null,

	@field:SerializedName("last_name")
	val lastName: String? = null,

	@field:SerializedName("auth_key")
	val authKey: String? = null,

	@field:SerializedName("first_name")
	val firstName: String? = null
)
