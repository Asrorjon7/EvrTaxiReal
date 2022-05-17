package info.texnoman.evrtaxireal.model.request

import com.google.gson.annotations.SerializedName

data class SetNewOrderRequest(

	@field:SerializedName("mode")
	val mode: Int? = null,

	@field:SerializedName("cost")
	val cost: Int? = null,

	@field:SerializedName("latitude")
	val latitude: String? = null,

	@field:SerializedName("count")
	val count: Int? = null,

	@field:SerializedName("country1")
	val country1: Int? = null,

	@field:SerializedName("comment")
	val comment: String? = null,

	@field:SerializedName("type")
	val type: Int? = null,

	@field:SerializedName("when")
	val when1: Int? = null,

	@field:SerializedName("longitude")
	val longitude: String? = null,

	@field:SerializedName("country2")
	val country2: Int? = null
)
