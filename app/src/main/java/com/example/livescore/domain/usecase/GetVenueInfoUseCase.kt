package com.example.livescore.domain.usecase

import com.example.livescore.data.model.venue.Venue

import com.example.livescore.domain.repository.CricRadioRepository
import javax.inject.Inject

class GetVenueInfoUseCase @Inject constructor(
    private val repository: CricRadioRepository
) {
    suspend operator fun invoke(key: String): Venue? {
        return repository.getVenueInfo(key)
    }
}