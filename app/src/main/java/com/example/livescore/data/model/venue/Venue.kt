package com.example.livescore.data.model.venue

import com.example.livescore.data.model.minicard.Minicard
import kotlinx.serialization.Serializable


@Serializable
data class Venue(
    var requestParams: RequestParams?,
    var responseData: ResponseData?,
    var statusCode: Int?,
    var time: String?
){

    constructor() : this(
        requestParams=null,
        responseData=null,
        statusCode=null,
        time=null
    )
}