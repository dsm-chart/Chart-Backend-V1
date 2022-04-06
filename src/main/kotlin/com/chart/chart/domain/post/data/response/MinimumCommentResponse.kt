package com.chart.chart.domain.post.data.response

import com.chart.chart.domain.account.data.response.MinimumUserResponse

data class MinimumCommentResponse(
    val content: String,
    val writer: MinimumUserResponse,
)
