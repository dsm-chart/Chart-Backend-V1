package com.chart.chart.global.exception

import com.chart.chart.global.exception.base.type.ErrorCode
import com.chart.chart.global.exception.base.GlobalException

class UserAlreadyExistsException(data: String): GlobalException(data, ErrorCode.USER_ALREADY_EXISTS) {
}