package com.chart.chart.infra.github.exception

import com.chart.chart.global.exception.base.GlobalException
import com.chart.chart.global.exception.base.type.ErrorCode

class GithubUnAuthorizeException(data: String): GlobalException(data, ErrorCode.GITHUB_UNAUTHORIZED) {
}