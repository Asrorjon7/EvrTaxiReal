package info.texnoman.evrtaxireal.model

import com.google.gson.annotations.SerializedName

data class YandexModel(

	@field:SerializedName("response")
	val response: Response? = null
)

data class Envelope(

	@field:SerializedName("lowerCorner")
	val lowerCorner: String? = null,

	@field:SerializedName("upperCorner")
	val upperCorner: String? = null
)

data class AdministrativeArea(

	@field:SerializedName("AdministrativeAreaName")
	val administrativeAreaName: String? = null,

	@field:SerializedName("SubAdministrativeArea")
	val subAdministrativeArea: SubAdministrativeArea? = null
)

data class GeoObject(

	@field:SerializedName("metaDataProperty")
	val metaDataProperty: MetaDataProperty? = null,

	@field:SerializedName("boundedBy")
	val boundedBy: BoundedBy? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("Point")
	val point: Point? = null,

	@field:SerializedName("description")
	val description: String? = null
)

data class ComponentsItem(

	@field:SerializedName("kind")
	val kind: String? = null,

	@field:SerializedName("name")
	val name: String? = null
)

data class Locality(

	@field:SerializedName("LocalityName")
	val localityName: String? = null,

	@field:SerializedName("Thoroughfare")
	val thoroughfare: Thoroughfare? = null
)

data class SubAdministrativeArea(

	@field:SerializedName("SubAdministrativeAreaName")
	val subAdministrativeAreaName: String? = null,

	@field:SerializedName("Locality")
	val locality: Locality? = null
)

data class Address(

	@field:SerializedName("Components")
	val components: List<ComponentsItem?>? = null,

	@field:SerializedName("country_code")
	val countryCode: String? = null,

	@field:SerializedName("formatted")
	val formatted: String? = null
)

data class Response(

	@field:SerializedName("GeoObjectCollection")
	val geoObjectCollection: GeoObjectCollection? = null
)

data class GeocoderResponseMetaData(

	@field:SerializedName("request")
	val request: String? = null,

	@field:SerializedName("found")
	val found: String? = null,

	@field:SerializedName("Point")
	val point: Point? = null,

	@field:SerializedName("results")
	val results: String? = null
)

data class GeocoderMetaData(

	@field:SerializedName("Address")
	val address: Address? = null,

	@field:SerializedName("AddressDetails")
	val addressDetails: AddressDetails? = null,

	@field:SerializedName("kind")
	val kind: String? = null,

	@field:SerializedName("precision")
	val precision: String? = null,

	@field:SerializedName("text")
	val text: String? = null
)

data class MetaDataProperty(

	@field:SerializedName("GeocoderResponseMetaData")
	val geocoderResponseMetaData: GeocoderResponseMetaData? = null,

	@field:SerializedName("GeocoderMetaData")
	val geocoderMetaData: GeocoderMetaData? = null
)

data class Premise(

	@field:SerializedName("PremiseNumber")
	val premiseNumber: String? = null
)

data class Point(

	@field:SerializedName("pos")
	val pos: String? = null
)

data class AddressDetails(

	@field:SerializedName("Country")
	val country: Country? = null
)

data class GeoObjectCollection(

	@field:SerializedName("metaDataProperty")
	val metaDataProperty: MetaDataProperty? = null,

	@field:SerializedName("featureMember")
	val featureMember: List<FeatureMemberItem?>? = null
)

data class FeatureMemberItem(

	@field:SerializedName("GeoObject")
	val geoObject: GeoObject? = null
)

data class Country(

	@field:SerializedName("CountryName")
	val countryName: String? = null,

	@field:SerializedName("AddressLine")
	val addressLine: String? = null,

	@field:SerializedName("CountryNameCode")
	val countryNameCode: String? = null,

	@field:SerializedName("AdministrativeArea")
	val administrativeArea: AdministrativeArea? = null
)

data class Thoroughfare(

	@field:SerializedName("ThoroughfareName")
	val thoroughfareName: String? = null,

	@field:SerializedName("Premise")
	val premise: Premise? = null
)

data class BoundedBy(

	@field:SerializedName("Envelope")
	val envelope: Envelope? = null
)
