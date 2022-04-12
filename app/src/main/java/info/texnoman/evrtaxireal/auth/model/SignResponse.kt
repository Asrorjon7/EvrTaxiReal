package info.texnoman.evrtaxireal.auth.model

import com.google.gson.annotations.SerializedName

data class SignResponse(

	@field:SerializedName("updated_at")
	val updatedAt: String? = null,

	@field:SerializedName("phone")
	val phone: String? = null,

	@field:SerializedName("sex")
	val sex: String? = null,

	@field:SerializedName("last_name")
	val lastName: String? = null,

	@field:SerializedName("created_at")
	val createdAt: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("auth_key")
	val authKey: String? = null,

	@field:SerializedName("type")
	val type: String? = null,

	@field:SerializedName("first_name")
	val firstName: String? = null,

	@field:SerializedName("status")
	val status: String? = null
)
