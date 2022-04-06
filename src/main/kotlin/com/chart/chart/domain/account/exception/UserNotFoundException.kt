package com.chart.chart.domain.account.exception

import com.chart.chart.global.exception.base.type.ErrorCode
import com.chart.chart.global.exception.base.GlobalException

class UserNotFoundException(data: String): GlobalException(data, ErrorCode.USER_NOT_FOUND) {
}
