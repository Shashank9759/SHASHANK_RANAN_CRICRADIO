package com.example.livescore.domain.repository

import com.example.livescore.data.model.minicard.Minicard
import com.example.livescore.data.model.venue.Venue


interface CricRadioRepository {
    suspend fun getMiniMatchCard(key: String): Minicard?
    suspend fun getVenueInfo(key: String): Venue?
}
