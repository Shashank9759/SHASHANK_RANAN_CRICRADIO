package com.example.livescore.data.model.venue

import kotlinx.serialization.Serializable


@Serializable
data class ResponseData(
    val message: String,
    val result: Result
)