package com.example.resoweatherapp.data.db.entity
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.example.resoweatherapp.data.db.Converters
import com.google.gson.annotations.SerializedName

const val CURRENT_WEATHER_ID = 0

@Entity(tableName = "current_weather")
@TypeConverters(Converters::class)
data class CurrentWeatherEntry(
    @SerializedName("feelslike")
    val feelslike: Double,
    @SerializedName("is_day")
    val isDay: String,
    @SerializedName("observation_time")
    val observationTime: String,
    @SerializedName("precip")
    val precip: Double,
    @SerializedName("temperature")
    val temperature: Double,
    @SerializedName("uv_index")
    val uvIndex: Double,
    @SerializedName("visibility")
    val visibility: Double,
    @SerializedName("weather_code")
    val weatherCode: Int,
    @SerializedName("weather_descriptions")
    val weatherDescriptions: List<String>,
    @SerializedName("weather_icons")
    val weatherIcons: List<String>,
    @SerializedName("wind_dir")
    val windDir: String,
    @SerializedName("wind_speed")
    val windSpeed: Double
) {
    //We only have 1 single current weather at all time
    @PrimaryKey(autoGenerate = false)
    var id: Int = CURRENT_WEATHER_ID
}