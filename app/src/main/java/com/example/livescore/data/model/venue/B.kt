package com.example.livescore.data.model.venue

import com.example.livescore.data.model.venue.A
import kotlinx.serialization.Serializable

@Serializable
data class B(
    val logo: String?,
    val name: String?,
    val shortName: String?
){

    constructor() : this(

        logo = null,
        name = null,
        shortName=null

    )
}