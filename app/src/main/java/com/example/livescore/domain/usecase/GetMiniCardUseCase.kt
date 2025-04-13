package com.example.livescore.domain.usecase


import com.example.livescore.data.model.minicard.Minicard
import com.example.livescore.domain.repository.CricRadioRepository
import javax.inject.Inject

class GetMiniCardUseCase @Inject constructor(
    private val repository: CricRadioRepository
) {
    suspend operator fun invoke(key: String): Minicard? {
        return repository.getMiniMatchCard(key)
    }
}