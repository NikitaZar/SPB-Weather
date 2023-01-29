package ru.nikitazar.spbweather.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import ru.nikitazar.spbweather.R
import ru.nikitazar.spbweather.databinding.FragmentHomeBinding
import ru.nikitazar.spbweather.viewModel.WeatherViewModel

@AndroidEntryPoint
class HomeFragment : Fragment() {

    lateinit var binding: FragmentHomeBinding
    private val viewModel: WeatherViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        viewModel.dataWeather.observe(viewLifecycleOwner) {
            binding.text.text = it.name
        }

        return binding.root
    }
}