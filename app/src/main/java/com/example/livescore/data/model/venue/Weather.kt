package com.example.livescore.data.model.venue

import kotlinx.serialization.Serializable


@Serializable
data class Weather(
    val chance_of_rain: Int,
    val condition: Condition,
    val last_updated: String,
    val location: String,
    val temp_c: Double
)