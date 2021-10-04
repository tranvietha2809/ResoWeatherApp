package com.example.resoweatherapp.data.db.unitlocalized
import androidx.room.TypeConverters
import com.example.resoweatherapp.data.db.Converters
import com.google.gson.annotations.SerializedName

@TypeConverters(Converters::class)
class MetricCurrentWeatherEntry(
    @SerializedName("feelslike")
    override val feelslike: Double,
    @SerializedName("is_day")
    override val isDay: String,
    @SerializedName("observation_time")
    override val observationTime: String,
    @SerializedName("precip")
    override val precip: Double,
    @SerializedName("temperature")
    override val temperature: Double,
    @SerializedName("uv_index")
    override val uvIndex: Double,
    @SerializedName("visibility")
    override val visibility: Double,
    @SerializedName("weather_code")
    override val weatherCode: Int,
    @SerializedName("weather_descriptions")
    override val weatherDescriptions: List<String>,
    @SerializedName("weather_icons")
    override val weatherIcons: List<String>,
    @SerializedName("wind_dir")
    override val windDir: String,
    @SerializedName("wind_speed")
    override val windSpeed: Double
) : UnitSpecificCurrentWeatherEntry