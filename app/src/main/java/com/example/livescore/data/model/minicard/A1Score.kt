package com.example.livescore.data.model.minicard

import kotlinx.serialization.Serializable

@Serializable
data class A1Score(
    val declare: Boolean,
    val overs: String,
    val runs: Int,
    val wickets: Int
)