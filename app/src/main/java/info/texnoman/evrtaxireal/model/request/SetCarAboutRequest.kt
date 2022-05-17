package info.texnoman.evrtaxireal.model.request

import com.google.gson.annotations.SerializedName

data class SetCarAboutRequest(

	@field:SerializedName("car_model_id")
	val carModelId: Int? = null,

	@field:SerializedName("car_number")
	val carNumber: String? = null,

	@field:SerializedName("country1")
	val country1: Int? = null,

	@field:SerializedName("type")
	val type: Int? = null,

	@field:SerializedName("token")
	val token: String? = null,

	@field:SerializedName("license_number")
	val licenseNumber: String? = null,

	@field:SerializedName("country2")
	val country2: Int? = null
)
