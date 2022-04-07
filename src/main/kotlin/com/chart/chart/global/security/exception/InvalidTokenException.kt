package com.chart.chart.global.security.exception

import com.chart.chart.global.exception.base.type.ErrorCode
import com.chart.chart.global.exception.base.GlobalException

class InvalidTokenException(data: String): GlobalException(data, ErrorCode.INVALID_TOKEN) {
}