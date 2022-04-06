package com.chart.chart.global.security.exception

import com.chart.chart.global.exception.base.type.ErrorCode
import com.chart.chart.global.exception.base.GlobalException

class ExpiredTokenException(data: String): GlobalException(data, ErrorCode.EXPIRED_TOKEN) {
}