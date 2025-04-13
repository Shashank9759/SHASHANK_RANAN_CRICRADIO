package com.example.livescore.data.model.minicard

import kotlinx.serialization.Serializable

@Serializable
data class Now(
    val req_run_rate: String,
    val run_rate: String,
//    val sessionLeft: Any
)