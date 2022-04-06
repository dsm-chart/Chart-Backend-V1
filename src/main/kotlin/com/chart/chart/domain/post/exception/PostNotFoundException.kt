package com.chart.chart.domain.post.exception

import com.chart.chart.global.exception.base.type.ErrorCode
import com.chart.chart.global.exception.base.GlobalException

class PostNotFoundException(data: String): GlobalException(data, ErrorCode.POST_NOT_FOUND) {
}