package com.example.weather.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.weather.databinding.FragmentAboutBinding
import com.example.weather.databinding.FragmentDetailsBinding
import com.example.weather.viewmodel.MainViewModel
// TODO: 20.02.2022  возврат из фрагмента - доработать логику м
class AboutFragment:Fragment() {
    private lateinit var viewModel: MainViewModel

    private var _binding: FragmentAboutBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAboutBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}