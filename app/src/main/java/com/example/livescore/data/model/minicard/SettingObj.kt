package com.example.livescore.data.model.minicard

import kotlinx.serialization.Serializable


@Serializable
data class SettingObj(
    val currentInning: Int,
    val currentTeam: String
)