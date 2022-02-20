package com.example.weather.view


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.weather.R
import com.example.weather.databinding.FragmentDetailsBinding
import com.example.weather.model.Weather
import com.example.weather.model.getConditions
import com.example.weather.model.getWinDir
import com.example.weather.viewmodel.MainViewModel
import com.yandex.mapkit.MapKitFactory


class DetailsFragment : Fragment(), View.OnClickListener {

    companion object {

        const val BUNDLE_EXTRA = "weather"

        fun newInstance(bundle: Bundle): DetailsFragment {
            val fragment = DetailsFragment()
            fragment.arguments = bundle
            return fragment
        }
    }


    private lateinit var viewModel: MainViewModel

    private var _binding: FragmentDetailsBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val weather = arguments?.getParcelable<Weather>(BUNDLE_EXTRA)
        if (weather != null) {
            val city = weather.city
            binding.loadingLayout.visibility = View.GONE
            binding.cityName.text = city.city
            binding.temperatureValue.text = "${weather.toString(weather.temperature)}\u00B0"
            binding.feelsLike.text = "Ощущается как  ${weather.toString(weather.feelsLike)}°"
            binding.condition.text = getConditions(weather.condition)
            binding.windDir.text = "Ветер ${getWinDir(weather.windDir)}"
            binding.windSpeed.text = "Скорость ветра ${weather.windSpeed} м/с"
            binding.pressure?.text = "Давление ${weather.pressure} мм рт. ст."
            binding.humidity?.text = "Влажность ${weather.humidity} %"

        }
        binding.search?.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        val manager = activity?.supportFragmentManager
        if (manager != null) {
            val bundle = Bundle()
            bundle.putParcelable(MapFragment.BUNDLE_EXTRA, arguments?.getParcelable<Weather>(BUNDLE_EXTRA))
            manager.beginTransaction()
                .replace(R.id.container, MapFragment.newInstance(bundle))
                .addToBackStack("")
                .commit()
        }

    }


}