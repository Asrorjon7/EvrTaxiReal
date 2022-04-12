package info.texnoman.evrtaxireal._user.viewmodel

import android.Manifest
import android.app.Application
import android.content.pm.PackageManager
import android.location.Address
import android.location.Geocoder
import android.os.Looper
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.android.gms.location.*
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.LatLng
import info.texnoman.evrtaxireal.base.BaseViewModel
import java.util.*
import javax.inject.Inject

class SearchDriverViewModel @Inject constructor(application: Application) :
    BaseViewModel(application) {
    var context = application.applicationContext

    private var _currentLocation: MutableLiveData<LatLng> = MutableLiveData()
    val currentLocation: LiveData<LatLng> = _currentLocation
    private var _getCurrentAddress: MutableLiveData<String> = MutableLiveData()
    val getCurrentAddress: LiveData<String> = _getCurrentAddress

    private var _resultSumlive:MutableLiveData<Int> = MutableLiveData()
    val resultSumLive:LiveData<Int> =_resultSumlive

    fun setIncrement(resultSum:Int,sum:Int){
       var add =resultSum +sum
        _resultSumlive.value = add
    }
    fun setDecrement(resultSum:Int,sum:Int){
        if (resultSum>sum){
            var split =resultSum -sum
            _resultSumlive.value = split
        }else{
            _resultSumlive.value = resultSum
        }
    }

    fun fusedLocation(mMap: GoogleMap) {
        var currentLatLng: LatLng? = null
        var fusedLocationProviderClient = FusedLocationProviderClient(context)
        var locationRequest = LocationRequest.create().apply {
            interval = 60000
            fastestInterval = 60000
            priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        }

        var locationcallback = object : LocationCallback() {
            override fun onLocationResult(locationresult: LocationResult) {
                super.onLocationResult(locationresult)
                locationresult ?: return
                Log.e("currentLocation", currentLatLng.toString())

                currentLatLng = LatLng(
                    locationresult.locations[locationresult.locations.size - 1].latitude,
                    locationresult.locations[locationresult.locations.size - 1].longitude
                )
                _currentLocation.value = currentLatLng!!
                getAddress(currentLatLng!!)
                for (location in locationresult.locations) {
                    if (currentLatLng == null) {

                    }
                }
                 /* mMap.animateCamera(
                     CameraUpdateFactory.newLatLng(locationresult.locations), 500, null
                 )*/

            }
        }

        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return
        }
        mMap.isMyLocationEnabled = true
        fusedLocationProviderClient?.requestLocationUpdates(
            locationRequest,
            locationcallback,
            Looper.myLooper()
        )
    }

    fun getAddress(l: LatLng) {
        if (l != null) {
            val lat = l.latitude
            val lon = l.longitude
            val geocoder = Geocoder(context, Locale.getDefault())
            val addresses: List<Address> = geocoder.getFromLocation(lat, lon, 1)
            val address = addresses[0].getAddressLine(0)
            val address2 = addresses[0].getAddressLine(1)
            val city = addresses[0].locality
            val state = addresses[0].adminArea
            val country = addresses[0].countryName
            val postalCode = addresses[0].postalCode
            val knownName = addresses[0].featureName
          //  val knownName1 = addresses[0].locale
            val message ="Emergency situation. Call for help. My location is: $address.http://maps.google.com/maps?saddr=$lat,$lon"
            Log.e("address", "$city $state")
            _getCurrentAddress.value = "$city"
        }
    }


}
