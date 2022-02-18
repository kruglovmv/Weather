package com.example.weather.view


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.weather.databinding.FragmentDetailsBinding
import com.example.weather.model.Weather
import com.example.weather.viewmodel.MainViewModel


class DetailsFragment : Fragment() {

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
            binding.condition.text = "Облачно"
            binding.windDir.text = "Ветер северный"
            binding.windSpeed.text = "Скорость ветра 1 м/с"
        }
    }


}