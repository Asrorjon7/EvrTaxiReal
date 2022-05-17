package info.texnoman.evrtaxireal._user.ui.SearchDriver

import android.annotation.SuppressLint
import android.app.Activity.RESULT_OK
import android.content.Intent
import android.location.Address
import android.location.Geocoder
import android.os.Build
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.navigation.Navigation
import com.google.android.gms.common.api.Status
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import info.texnoman.evrtaxireal.R
import info.texnoman.evrtaxireal._user.viewmodel.SearchDriverViewModel
import info.texnoman.evrtaxireal.base.BaseFragment
import info.texnoman.evrtaxireal.databinding.FragmentSearchDirectionBinding
import info.texnoman.evrtaxireal.di.factory.injectViewModel


import com.google.android.material.bottomsheet.BottomSheetBehavior
import info.texnoman.evrtaxireal._user.main.UserActivity
import info.texnoman.evrtaxireal.databinding.BottomSheetForMapBinding
import info.texnoman.evrtaxireal.utils.toNumberFormatString
import info.texnoman.evrtaxireal.utils.LatLngInterpolator

import info.texnoman.evrtaxireal.utils.MarkerAnimation

import com.google.android.gms.maps.model.BitmapDescriptorFactory

import com.google.android.gms.maps.model.MarkerOptions

import com.google.android.gms.maps.model.LatLng
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.api.net.PlacesClient
import com.google.android.libraries.places.widget.Autocomplete
import com.google.android.libraries.places.widget.AutocompleteSupportFragment
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode
import info.texnoman.evrtaxireal.utils.Extensions.alert
import info.texnoman.evrtaxireal.utils.LatLngInterpolator.Spherical
import java.io.IOException
import java.util.*
import kotlin.collections.ArrayList

const val TAG = "SearchFragment"

class SearchDirectionFragment :
    BaseFragment<FragmentSearchDirectionBinding, SearchDriverViewModel>()
  //  OnMapReadyCallback
{
    lateinit var mapFragment: SupportMapFragment

    private lateinit var mMap: GoogleMap

    override fun injectViewModel() {
        mViewModel = injectViewModel(viewModelFactory)
    }

    override fun getViewModelClass(): Class<SearchDriverViewModel> =
        SearchDriverViewModel::class.java

    @SuppressLint("ClickableViewAccessibility")
    override fun init() {
        (requireActivity() as UserActivity).hideActionBar()

     //   loadMap()

        bottomSheetDialog()

        cancelOrder()

    }

    private fun cancelOrder() {

        binding.cancelOrder.setOnClickListener {
            alert("", "Siz rostan ham buyurtmani bekor qilib yubormoqchimisiz?") {
                positiveButton("Xa") {
                    alert("", "Buyurtma bekor qilindi") {
                        backto("Ortga qaytish") {
                            Navigation.findNavController(requireView()).popBackStack()
                        }
                    }.show()
                }
                negativeButton("Yo'q") {
                }
            }.show()
        }
    }

    override fun setupViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentSearchDirectionBinding =
        FragmentSearchDirectionBinding.inflate(inflater, container, false)

  /*  override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        mViewModel.fusedLocation(mMap)
        mViewModel.currentLocation.observe(viewLifecycleOwner, { latlang ->
            mMap.animateCamera(CameraUpdateFactory.zoomBy(14F), 500, null)
            mMap.moveCamera(CameraUpdateFactory.newLatLng(latlang))
        })
    }*/

   /* private fun loadMap() {
        mapFragment = (childFragmentManager.findFragmentById(R.id.mapAPI) as SupportMapFragment?)!!
        val fm = childFragmentManager
        val ft = fm.beginTransaction()
        mapFragment = SupportMapFragment.newInstance()
        ft.replace(R.id.mapAPI, mapFragment).commit()
        mapFragment.getMapAsync(this)
    }*/

    @SuppressLint("ClickableViewAccessibility")
    private fun bottomSheetDialog() {

        binding.etQayerdan.setOnTouchListener { _, motionEvent ->
            if (motionEvent?.action == MotionEvent.ACTION_UP) {
                Log.e("bosildida", "editText bosildi")
            }
            false
        }
        mViewModel.getCurrentAddress.observe(viewLifecycleOwner) {
            binding.etQayerdan.setText(it.toString())
        }
        binding.apply {

            btnDecrement.setOnClickListener {
                mViewModel.setDecrement(
                    binding.tvResultSum.text.toString().replace(" ", "").toInt(), 2000
                )
            }
            btnIncrement.setOnClickListener {
                mViewModel.setIncrement(
                    binding.tvResultSum.text.toString().replace(" ", "").toInt(), 2000)
            }
            btnCostIncrease.setOnClickListener {
                (activity as UserActivity).offerShow()
            }


        }
        mViewModel.resultSumLive.observe(viewLifecycleOwner) {
            binding.tvResultSum.text = it.toNumberFormatString()
            binding.tvSumm.text = it.toNumberFormatString()
        }
    }
    override fun onDestroy() {
        super.onDestroy()

        (requireActivity() as UserActivity).showActionBar()


    }
/*
    private fun searchWhere() {
        binding.etQayerdan.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                Log.e("onTextChange", p0.toString())
                var location = p0.toString()
                var addressList: List<Address>? = ArrayList()
                if (location != null) {
                    var geocoder = Geocoder(requireContext())
                    try {
                        addressList = geocoder.getFromLocationName(location, 1)

                    } catch (e: IOException) {
                        e.printStackTrace()
                    }
                    if (addressList?.size!! > 0) {
                        var getAddress = addressList!![0]?.countryName.toString()
                        Log.e("address", getAddress.toString())
                    }

                }
            }

            override fun afterTextChanged(p0: Editable?) {

            }

        })
    }*/


}