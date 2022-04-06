package com.chart.chart.global.exception.base

import com.chart.chart.global.exception.base.type.ErrorCode
import java.lang.RuntimeException

open class GlobalException(data: String, errorCode: ErrorCode): RuntimeException(data) {
    val data = data
    val errorCode = errorCode
}