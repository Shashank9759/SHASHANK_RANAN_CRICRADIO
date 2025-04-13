package com.example.livescore.presentation.viewmodel

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.livescore.domain.usecase.WebSocketUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

@HiltViewModel
class WebSocketViewModel @Inject constructor(
    private val webSocketUseCase: WebSocketUseCase
) : ViewModel() {

    var receivedMessage by mutableStateOf("")
        private set

    var connectionError by mutableStateOf<String?>(null)
        private set

    fun connectWebSocket() {
        viewModelScope.launch {
            webSocketUseCase.connect(
                onReceive = { message ->
                    receivedMessage = message
                },
                onError = { error ->
                    connectionError = error.localizedMessage
                    Log.d("Tagggggg",connectionError.toString())
                }
            )
        }
    }

    fun sendMessage(message: String) {
        viewModelScope.launch {
            try {
                webSocketUseCase.sendMessage(message)
            } catch (e: Exception) {
                connectionError = e.localizedMessage
            }
        }
    }

    fun disconnectWebSocket() {
        viewModelScope.launch {
            webSocketUseCase.disconnect()
        }
    }
}
