package info.texnoman.evrtaxireal.model.response

import com.google.gson.annotations.SerializedName

data class CarAboutResponse(

	@field:SerializedName("phone")
	val phone: String? = null,

	@field:SerializedName("sex")
	val sex: String? = null,

	@field:SerializedName("last_name")
	val lastName: String? = null,

	@field:SerializedName("photo")
	val photo: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("fullname")
	val fullname: String? = null,

	@field:SerializedName("auth_key")
	val authKey: String? = null,

	@field:SerializedName("type")
	val type: String? = null,

	@field:SerializedName("first_name")
	val firstName: String? = null,

	@field:SerializedName("status")
	val status: String? = null
)
