package com.example.livescore.data.model.venue

import kotlinx.serialization.Serializable


@Serializable
data class VenueDetails(
    val _id: String,
    val capacity: Int,
    val createdAt: String,
    val cricinfoId: Int,
    val description: String,
    val ends1: String,
    val ends2: String,
    val floodLights: Int,
    val homeTo: String,
    val isDeleted: String,
    val knownAs: String,
 //   val opened: Any,
    val photo: String,
    val status: String,
    val timezone: String,
    val updatedAt: String,
    val venueLocation: String,
    val venueScraptitle: String,
    val venue_info: VenueInfo
)