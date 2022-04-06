package com.chart.chart.domain.account.data.request

data class SignupRequest(
    val githubCode: String,
    val schoolCode: String,
    val areaCode: String,
    val grade: Int,
    val classNum: Int

)
