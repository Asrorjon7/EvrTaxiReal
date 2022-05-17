package info.texnoman.evrtaxireal._user.ui.LocationChoose

import android.Manifest
import android.content.pm.PackageManager
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MapStyleOptions
import info.texnoman.evrtaxireal.R
import info.texnoman.evrtaxireal._user.viewmodel.UserViewModel
import info.texnoman.evrtaxireal.base.BaseFragment
import info.texnoman.evrtaxireal.databinding.FragmentLocationChooseBinding
import info.texnoman.evrtaxireal.di.factory.injectViewModel


class LocationChooseFragment : BaseFragment<FragmentLocationChooseBinding, UserViewModel>(),
    OnMapReadyCallback {
    lateinit var mapApi: GoogleMap
    lateinit var mapFragment: SupportMapFragment
    lateinit var location: LatLng

    override fun injectViewModel() {
        mViewModel = injectViewModel(viewModelFactory)
    }

    override fun getViewModelClass(): Class<UserViewModel> = UserViewModel::class.java

    override fun init() {
        location = arguments?.get("location") as LatLng
        loadMap()
        binding.ivlocation.setOnClickListener {
            mapApi.animateCamera(CameraUpdateFactory.newLatLngZoom(location, 17f), 1000, null)
        }
        binding.getLocation.setOnClickListener {
            var latitude = mapApi.cameraPosition.target.latitude
            var lontittude = mapApi.cameraPosition.target.longitude
            Log.e("longlat", latitude.toString() + lontittude.toString())
        }

    }

    override fun setupViewBinding(inflater: LayoutInflater, container: ViewGroup?):
            FragmentLocationChooseBinding {
        return FragmentLocationChooseBinding.inflate(inflater, container, false)
    }

    private fun loadMap() {
        mapFragment = (childFragmentManager.findFragmentById(R.id.mapAPI) as SupportMapFragment?)!!
        val fm = childFragmentManager
        val ft = fm.beginTransaction()
        mapFragment = SupportMapFragment.newInstance()
        ft.replace(R.id.mapAPI, mapFragment).commit()
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mapApi = googleMap
        mapApi.mapType = GoogleMap.MAP_TYPE_TERRAIN
        mapApi.setMapStyle(
            MapStyleOptions.loadRawResourceStyle(
                requireContext(),
                R.raw.custom_maps
            )
        )
        mapApi.animateCamera(CameraUpdateFactory.newLatLngZoom(location, 17f), 100, null)

        if (ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }
        mapApi.isMyLocationEnabled = true
        //  mapApi.isBuildingsEnabled=true
    }
}