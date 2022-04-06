package com.chart.chart.global.exception.base.response

import org.springframework.http.HttpStatus

data class ErrorResponse(
    val message: String,
    val status: HttpStatus,
    val data: String

)
