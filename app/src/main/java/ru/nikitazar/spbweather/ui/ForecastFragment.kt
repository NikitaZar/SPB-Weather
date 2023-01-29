package ru.nikitazar.spbweather.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import ru.nikitazar.spbweather.R
import ru.nikitazar.spbweather.databinding.FragmentDetailsBinding
import ru.nikitazar.spbweather.databinding.FragmentForecastBinding
import ru.nikitazar.spbweather.databinding.FragmentHomeBinding
import ru.nikitazar.spbweather.viewModel.WeatherViewModel

@AndroidEntryPoint
class ForecastFragment : Fragment() {

    lateinit var binding: FragmentForecastBinding
    private val viewModel: WeatherViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentForecastBinding.inflate(inflater, container, false)

        return binding.root
    }
}