package com.example.livescore.data.model.minicard

import kotlinx.serialization.Serializable

@Serializable
data class Minicard(
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