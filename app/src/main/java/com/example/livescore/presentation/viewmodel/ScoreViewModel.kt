package com.example.livescore.presentation.viewmodel

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import com.example.livescore.domain.usecase.GetMiniCardUseCase
import com.example.livescore.domain.usecase.GetVenueInfoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import com.example.livescore.data.model.minicard.Minicard
import com.example.livescore.data.model.venue.Venue


@HiltViewModel
class ScoreViewModel @Inject constructor(
    private val getMiniCardUseCase: GetMiniCardUseCase,
    private val getVenueInfoUseCase: GetVenueInfoUseCase
) : ViewModel() {

    var miniCard by mutableStateOf<Minicard?>(null)
        private set

    var venueInfo by mutableStateOf<Venue?>(null)
        private set


    var isLoading by mutableStateOf(false)
    var errorMessage by mutableStateOf<String?>(null)

    fun loadMatchData(key: String) {
        viewModelScope.launch {
            try {
                isLoading = true
                val miniCardResult = getMiniCardUseCase(key)
                val venueResult = getVenueInfoUseCase(key)
                miniCard = miniCardResult
                venueInfo = venueResult
                errorMessage = null
            } catch (e: Exception) {
                errorMessage = e.localizedMessage ?: "Unknown error"
                Log.d("Tagggggg",e.message.toString() )
                Log.d("Tagggggg",e.toString() )
            } finally {
                isLoading = false
            }
        }
    }
}
