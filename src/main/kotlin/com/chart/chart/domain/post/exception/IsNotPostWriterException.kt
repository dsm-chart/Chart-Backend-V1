package com.chart.chart.domain.post.exception

import com.chart.chart.global.exception.base.type.ErrorCode
import com.chart.chart.global.exception.base.GlobalException

class IsNotPostWriterException(data: String): GlobalException(data, ErrorCode.IS_NOT_POST_WRITER) {
}