package info.texnoman.evrtaxireal.model.response

import com.google.gson.annotations.SerializedName

data class NewOrderResponse(

	@field:SerializedName("cost")
	val cost: Int? = null,

	@field:SerializedName("latitude")
	val latitude: String? = null,

	@field:SerializedName("country1")
	val country1: Int? = null,

	@field:SerializedName("count")
	val count: Int? = null,

	@field:SerializedName("created_at")
	val createdAt: CreatedAt? = null,

	@field:SerializedName("state2")
	val state2: Any? = null,

	@field:SerializedName("type")
	val type: Type? = null,

	@field:SerializedName("state1")
	val state1: Any? = null,

	@field:SerializedName("when")
	val when1: String? = null,

	@field:SerializedName("country2")
	val country2: Int? = null,

	@field:SerializedName("mode")
	val mode: Mode? = null,

	@field:SerializedName("updated_at")
	val updatedAt: UpdatedAt? = null,

	@field:SerializedName("comment")
	val comment: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("user")
	val user: User? = null,

	@field:SerializedName("longitude")
	val longitude: String? = null,

	@field:SerializedName("status")
	val status: Status? = null
)

data class Type(

	@field:SerializedName("number")
	val number: Int? = null,

	@field:SerializedName("string")
	val string: String? = null
)

data class User(

	@field:SerializedName("full_name")
	val fullName: String? = null,

	@field:SerializedName("phone")
	val phone: String? = null,

	@field:SerializedName("sex")
	val sex: String? = null,

	@field:SerializedName("id")
	val id: Int? = null
)

data class UpdatedAt(

	@field:SerializedName("number")
	val number: Int? = null,

	@field:SerializedName("string")
	val string: String? = null
)

data class Mode(

	@field:SerializedName("number")
	val number: Int? = null,

	@field:SerializedName("string")
	val string: String? = null
)

data class CreatedAt(

	@field:SerializedName("number")
	val number: Int? = null,

	@field:SerializedName("string")
	val string: String? = null
)

data class Status(

	@field:SerializedName("number")
	val number: Int? = null,

	@field:SerializedName("string")
	val string: String? = null
)
