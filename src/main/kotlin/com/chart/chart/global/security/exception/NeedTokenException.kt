package com.chart.chart.global.security.exception

import com.chart.chart.global.exception.base.GlobalException
import com.chart.chart.global.exception.base.type.ErrorCode

class NeedTokenException: GlobalException("NULL", ErrorCode.NEED_TOKEN) {
}