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
import ru.nikitazar.spbweather.viewModel.WeatherViewModel
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

@AndroidEntryPoint
class DetailsFragment : Fragment() {

    @Inject
    lateinit var calendar: Calendar

    lateinit var binding: FragmentDetailsBinding
    private val viewModel: WeatherViewModel by viewModels()
    private val df = DecimalFormat("#.#")
    private val formatter = SimpleDateFormat("dd.MM.yyyy HH:mm:ss", Locale.getDefault())
        .apply { timeZone = TimeZone.getDefault() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentDetailsBinding.inflate(inflater, container, false)
        viewModel.dataWeather.observe(viewLifecycleOwner) { data ->
            with(binding) {

                val time = calendar.apply { timeInMillis = data.dt }.time
                dt.text = formatter.format(time)

                weatherState.text = getString(R.string.weather_prefix_text, data.weather.first().main)
                temp.text = getString(R.string.temp_prefix_text, df.format(data.main.temp))
                feelsLike.text = getString(R.string.temp_feels_like_prefix_text, df.format(data.main.feelsLike))
                tempMax.text = getString(R.string.temp_max_prefix_text, df.format(data.main.tempMax))
                tempMin.text = getString(R.string.temp_min_prefix_text, df.format(data.main.tempMin))
                pressure.text = getString(R.string.pressure_prefix_text, df.format(data.main.pressure))
                humidity.text = getString(R.string.humidity_prefix_text, df.format(data.main.humidity))
                visibility.text = getString(R.string.visibility_prefix_text, df.format(data.visibility))
                windSpeed.text = getString(R.string.wind_speed_prefix_text, df.format(data.wind.speed))
                windDeg.text = getString(R.string.wind_deg_prefix_text, df.format(data.wind.deg))
                clouds.text = getString(R.string.clouds_prefix_text, data.clouds.all.toString())

                val sunriseTime = formatter.format(calendar.apply { timeInMillis = data.sys.sunrise }.time)
                sunrise.text = getString(R.string.sunrise_prefix_text, sunriseTime)

                val sunsetTime = formatter.format(calendar.apply { timeInMillis = data.sys.sunset }.time)
                sunset.text = getString(R.string.sunset_prefix_text, sunsetTime)
            }
        }



        return binding.root
    }
}