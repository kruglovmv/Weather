package com.example.weather.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.weather.R
import com.example.weather.databinding.FragmentMapBinding
import com.example.weather.model.Weather
import com.yandex.mapkit.Animation
import com.yandex.mapkit.MapKitFactory
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.mapview.MapView
import com.yandex.mapkit.map.CameraPosition

// TODO: 20.02.2022  поиск по наименованию
// пустая карта при выборе второго города через backstack

class MapFragment : Fragment() {
    companion object {

        const val BUNDLE_EXTRA = "weather"

        fun newInstance(bundle: Bundle): MapFragment {
            val fragment = MapFragment()
            fragment.arguments = bundle
            return fragment
        }
        fun newInstance(): MapFragment {
            val fragment = MapFragment()
            return fragment
        }
    }

    private var _binding: FragmentMapBinding? = null

    private val binding get() = _binding!!
    private lateinit var mapView: MapView

    object MapKitInitializer {

        private var initialized = false

        fun initialize(context: Context?) {
            if (initialized) {
                MapKitFactory.initialize(context)
                return
            }
            MapKitFactory.setApiKey(R.string.api_yandex_map_key.toString())
            MapKitFactory.initialize(context)
            initialized = true
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        MapKitInitializer.initialize(context)
        _binding = FragmentMapBinding.inflate(inflater, null, false)
        val view = binding.root
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        mapView.onStop()
        MapKitFactory.getInstance().onStop()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val lon: Double =
            arguments?.getParcelable<Weather>(DetailsFragment.BUNDLE_EXTRA)?.city?.lon ?: 37.573856
        val lat: Double =
            arguments?.getParcelable<Weather>(DetailsFragment.BUNDLE_EXTRA)?.city?.lat ?: 55.751574

        mapView = binding.mapview
        mapView.map.move(
            CameraPosition(Point(lat, lon), 11.0f, 0.0f, 0.0f),
            Animation(Animation.Type.SMOOTH, 0F),
            null
        )
    }

    override fun onStart() {
        super.onStart()
        MapKitFactory.getInstance().onStart()
        mapView.onStart()
    }
}