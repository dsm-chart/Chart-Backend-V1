package com.chart.chart.domain.post.exception

import com.chart.chart.global.exception.base.type.ErrorCode
import com.chart.chart.global.exception.base.GlobalException

class CommentNotExistsException(data: String): GlobalException(data, ErrorCode.COMMENT_NOT_FOUND) {
}