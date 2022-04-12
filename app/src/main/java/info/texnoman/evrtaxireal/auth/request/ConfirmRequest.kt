package info.texnoman.evrtaxireal.auth.request

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class ConfirmRequest(
	@field:SerializedName("code")
	val code: Int? = null,
	@field:SerializedName("confirm_token")
	val authKey: String? = null
) : Parcelable {
	constructor(parcel: Parcel) : this(
		parcel.readValue(Int::class.java.classLoader) as? Int,
		parcel.readString()
	) {
	}

	override fun writeToParcel(parcel: Parcel, flags: Int) {
		parcel.writeValue(code)
		parcel.writeString(authKey)
	}

	override fun describeContents(): Int {
		return 0
	}

	companion object CREATOR : Parcelable.Creator<ConfirmRequest> {
		override fun createFromParcel(parcel: Parcel): ConfirmRequest {
			return ConfirmRequest(parcel)
		}

		override fun newArray(size: Int): Array<ConfirmRequest?> {
			return arrayOfNulls(size)
		}
	}
}
