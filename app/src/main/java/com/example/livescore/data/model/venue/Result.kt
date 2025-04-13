package com.example.livescore.data.model.venue

import kotlinx.serialization.Serializable

@Serializable
data class Result(
    val _id: String,
    val firstUmpire: String,
    val format: String,
    val key: String,
    val matchReferee: String,
    val related_name: String,
    val season: Season,
    val secoundUmpire: String,
    val start_date: StartDate,
    val status: String,
    val teams: Teams,
    val thirdUmpire: String,
    val toss: Toss,
    val venue: String,
    val venueDetails: VenueDetails,
    val venueStats: VenueStats,
    val weather: Weather
)