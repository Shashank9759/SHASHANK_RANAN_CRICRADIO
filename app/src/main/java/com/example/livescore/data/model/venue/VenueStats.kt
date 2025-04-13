package com.example.livescore.data.model.venue

import com.example.livescore.Utils.StringOrNumberSerializer
import kotlinx.serialization.Serializable


@Serializable
data class VenueStats(
    val ballFirstWins: Int,
    val batFirstWins: Int,
    val battingFirst: BattingFirst,
    val battingSecond: BattingSecond,
    @Serializable(with = StringOrNumberSerializer::class)
    val highestChased: String,
    @Serializable(with = StringOrNumberSerializer::class)
    val lowestDefended: String,
    val matchesPlayed: Int
)