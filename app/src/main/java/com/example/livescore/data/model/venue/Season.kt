package com.example.livescore.data.model.venue

import kotlinx.serialization.Serializable


@Serializable
data class Season(
    val key: String,
    val name: String
)