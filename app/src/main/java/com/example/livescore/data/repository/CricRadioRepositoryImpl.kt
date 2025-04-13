package com.example.livescore.data.repository

import com.example.livescore.data.model.minicard.Minicard
import com.example.livescore.data.model.venue.Venue
import com.example.livescore.data.remote.CricRadioApi
import com.example.livescore.domain.repository.CricRadioRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CricRadioRepositoryImpl @Inject constructor(
    private val api: CricRadioApi
) : CricRadioRepository {

    override suspend fun getMiniMatchCard(key: String): Minicard? {
        return  api.getMiniMatchCard(key)

    }

    override suspend fun getVenueInfo(key: String): Venue? {
        return api.getVenueInfo(key)

    }
}
