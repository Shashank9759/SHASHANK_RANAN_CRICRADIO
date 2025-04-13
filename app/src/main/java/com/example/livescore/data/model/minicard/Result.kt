package com.example.livescore.data.model.minicard

import kotlinx.serialization.Serializable


@Serializable
data class Result(
    val announcement1: String,
 //   val announcement2: Any,
    val currentBattingOrder: Int,
    val format: String,
    val key: String,
    val lastCommentary: LastCommentary,
    val now: Now,
    val powerplay: String,
    val powerplayOver: Int,
    val settingObj: SettingObj,
    val status: String,
    val teams: Teams
)