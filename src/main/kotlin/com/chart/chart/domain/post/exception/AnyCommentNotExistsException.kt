package com.chart.chart.domain.post.exception

import com.chart.chart.global.exception.base.type.ErrorCode
import com.chart.chart.global.exception.base.GlobalException

class AnyCommentNotExistsException(data: String): GlobalException(data, ErrorCode.ANY_COMMENT_NOT_EXISTS) {
}