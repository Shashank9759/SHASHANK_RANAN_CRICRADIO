package com.example.livescore.data.remote

import com.example.livescore.data.model.minicard.Minicard
import com.example.livescore.data.model.venue.Venue


interface CricRadioApi {
    suspend fun getMiniMatchCard(key: String): Minicard
    suspend fun getVenueInfo(key: String): Venue
}
