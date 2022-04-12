package info.texnoman.evrtaxireal.auth.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName


data class ConfirmResponse(
	@field:SerializedName("status_code")
	val statusCode: Int? = null,

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
) : Parcelable {
	constructor(parcel: Parcel) : this(
		parcel.readValue(Int::class.java.classLoader) as? Int,
		parcel.readString(),
		parcel.readString(),
		parcel.readString(),
		parcel.readString(),
		parcel.readString(),
		parcel.readValue(Int::class.java.classLoader) as? Int,
		parcel.readString(),
		parcel.readString(),
		parcel.readString(),
		parcel.readString()
	) {
	}

	override fun writeToParcel(parcel: Parcel, flags: Int) {
		parcel.writeValue(statusCode)
		parcel.writeString(updatedAt)
		parcel.writeString(phone)
		parcel.writeString(sex)
		parcel.writeString(lastName)
		parcel.writeString(createdAt)
		parcel.writeValue(id)
		parcel.writeString(authKey)
		parcel.writeString(type)
		parcel.writeString(firstName)
		parcel.writeString(status)
	}

	override fun describeContents(): Int {
		return 0
	}

	companion object CREATOR : Parcelable.Creator<ConfirmResponse> {
		override fun createFromParcel(parcel: Parcel): ConfirmResponse {
			return ConfirmResponse(parcel)
		}

		override fun newArray(size: Int): Array<ConfirmResponse?> {
			return arrayOfNulls(size)
		}
	}
}
