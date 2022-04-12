package info.texnoman.evrtaxireal._user.model.response

import com.google.gson.annotations.SerializedName

data class RegionResponse(

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("id")
	val id: Int? = null
)
