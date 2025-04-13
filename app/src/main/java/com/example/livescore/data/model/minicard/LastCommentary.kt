package com.example.livescore.data.model.minicard

import kotlinx.serialization.Serializable


@Serializable
data class LastCommentary(
    val isDone: Boolean,
    val primaryText: String,
//    val secondaryText: Any,
//    val tertiaryText: Any
)