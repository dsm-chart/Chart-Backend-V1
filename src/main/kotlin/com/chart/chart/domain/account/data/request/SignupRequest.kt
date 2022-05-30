package com.chart.chart.domain.account.data.request

data class SignupRequest(
    val accessToken: String,
    val schoolCode: String,
    val areaCode: String,
    val grade: Int,
    val classNum: Int

)
