package ru.nikitazar.spbweather.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.nikitazar.domain.model.ForecastDataItemDomain
import ru.nikitazar.spbweather.R
import ru.nikitazar.spbweather.databinding.CardForecastBinding
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.*

private val df = DecimalFormat("#.#")
private val formatter = SimpleDateFormat("dd.MM.yyyy HH:mm:ss", Locale.US)
    .apply { timeZone = TimeZone.getDefault() }

class ForecastAdapter(private val calendar: Calendar) : ListAdapter<ForecastDataItemDomain, ForecastViewHolder>(ForecastDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForecastViewHolder {
        val binding = CardForecastBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ForecastViewHolder(binding, calendar)
    }

    override fun onBindViewHolder(holder: ForecastViewHolder, position: Int) {
        val forecast = getItem(position)
        holder.bind(forecast)
    }
}

class ForecastViewHolder(
    private val binding: CardForecastBinding,
    private val calendar: Calendar
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(item: ForecastDataItemDomain) {
        with(binding) {
            val time = calendar.apply { timeInMillis = item.dt }.time
            dt.text = formatter.format(time)

            temp.text = temp.context.getString(R.string.temp_prefix_forecast_text, df.format(item.temp))
        }
    }
}

class ForecastDiffCallback : DiffUtil.ItemCallback<ForecastDataItemDomain>() {

    override fun areItemsTheSame(oldItem: ForecastDataItemDomain, newItem: ForecastDataItemDomain) = oldItem.dt == newItem.dt

    override fun areContentsTheSame(oldItem: ForecastDataItemDomain, newItem: ForecastDataItemDomain) = oldItem == newItem

}