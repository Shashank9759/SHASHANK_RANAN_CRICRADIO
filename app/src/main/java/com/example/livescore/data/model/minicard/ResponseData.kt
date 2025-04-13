package com.example.livescore.data.model.minicard

import kotlinx.serialization.Serializable

@Serializable
data class ResponseData(
    val message: String,
    val result: Result
)