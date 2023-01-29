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
import ru.nikitazar.spbweather.databinding.FragmentDetailsBinding
import ru.nikitazar.spbweather.databinding.FragmentForecastBinding
import ru.nikitazar.spbweather.databinding.FragmentHomeBinding
import ru.nikitazar.spbweather.ui.adapters.ForecastAdapter
import ru.nikitazar.spbweather.view.SpacingItemDecorator
import ru.nikitazar.spbweather.viewModel.ForecastViewModel
import ru.nikitazar.spbweather.viewModel.WeatherViewModel
import java.util.*
import javax.inject.Inject

private const val ITEM_SPACE = 20

@AndroidEntryPoint
class ForecastFragment : Fragment() {

    @Inject
    lateinit var calendar: Calendar
    lateinit var binding: FragmentForecastBinding
    private val viewModel: ForecastViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentForecastBinding.inflate(inflater, container, false)

        val adapterForecast = ForecastAdapter(calendar)
        binding.list.apply {
            adapter = adapterForecast
            addItemDecoration(SpacingItemDecorator(ITEM_SPACE))
        }


        viewModel.dataForecast.observe(viewLifecycleOwner) {
            adapterForecast.submitList(it.list)
        }

        return binding.root
    }
}