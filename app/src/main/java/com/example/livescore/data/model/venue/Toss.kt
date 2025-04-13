package com.example.livescore.data.model.venue

import kotlinx.serialization.Serializable


@Serializable
data class Toss(
    val decision: String,
    val str: String,
    val won: String
)