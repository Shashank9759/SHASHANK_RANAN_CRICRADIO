package com.example.livescore.data.model.minicard

import kotlinx.serialization.Serializable

@Serializable
data class A(
    val a_1_score: A1Score,
    val a_2_score: A1Score,
    val logo: String,
    val name: String,
    val shortName: String) {



}