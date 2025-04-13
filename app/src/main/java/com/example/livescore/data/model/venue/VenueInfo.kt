package com.example.livescore.data.model.venue

import kotlinx.serialization.Serializable


@Serializable
data class VenueInfo(
    val location: String,
    val longName: String,
    val name: String,
    val smallName: String,
    val town: String
)