package com.example.livescore.data.model.venue

import kotlinx.serialization.Serializable

@Serializable
data class BattingSecond(
    val averageScore: Int,
    val highestScore: Int,
    val lowestScore: Int,
    val paceWickets: Int,
    val spinWickets: Int
)