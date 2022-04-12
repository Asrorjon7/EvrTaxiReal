package com.waheed.location.updates.livedata
import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.os.Looper
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.lifecycle.MutableLiveData
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices

const val INTERVAL = 20000L
const val FASTEST_INTERVAL = 20000L
class LocationLiveData( var context: Context) : MutableLiveData<Location>() {
    private var fusedLocationProviderClient =
        LocationServices.getFusedLocationProviderClient(context)
    /**
     * Sets the value. If there are active observers, the value will be dispatched to them.
     *
     * This method must be called from the main thread. If you need set a value from a background
     * thread, you can use postValue(Object)
     *
     */
    private fun setLocationData(location: Location) {
    //   Log.e("location",location.toString())
        value = location
    }

    /**
     * Static object of location request
     */
    companion object {
        val locationRequest: LocationRequest = LocationRequest.create()
            .apply {
                priority = LocationRequest.PRIORITY_HIGH_ACCURACY
            }
    }


    /**
     * Called when the number of active observers change to 1 from 0.
     * This callback can be used to know that this LiveData is being used thus should be kept
     * up to date.
     */
    override fun onInactive() {
        super.onInactive()
        fusedLocationProviderClient.removeLocationUpdates(locationCallback)
    }


    /**
     * Called when the number of active observers change from 1 to 0.
     *
     * This does not mean that there are no observers left, there may still be observers but their
     * lifecycle states aren't STARTED or RESUMED
     * (like an Activity in the back stack).
     *
     * You can check if there are observers via hasObservers() method
     */
    override fun onActive() {
        super.onActive()
        if (ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {

            Log.e("ruxsat berilmagan","ruxsat bering")

            return
        }
        fusedLocationProviderClient.lastLocation
            .addOnSuccessListener { location: Location? ->
                location?.also {
                    setLocationData(it)
                }
            }
        startLocationUpdates()
    }

    /**
     * Callback that triggers on location updates available
     */
    private val locationCallback = object : LocationCallback() {
        override fun onLocationResult(locationResult: LocationResult?) {
            locationResult ?: return
            for (location in locationResult.locations) {
                setLocationData(location)
            }
        }
    }

    /**
     * Initiate Location Updates using Fused Location Provider and
     * attaching callback to listen location updates
     */
    private fun startLocationUpdates() {
        if (ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            Log.e("ruxsat berilmagan","ruxsat bering")
                return
        }
        fusedLocationProviderClient.requestLocationUpdates(
            locationRequest,
            locationCallback,
            Looper.myLooper()
        )
    }


}
