package com.chart.chart.domain.post.data.response

import com.chart.chart.domain.account.data.response.MaximumUserResponse
import com.chart.chart.domain.account.data.response.MinimumUserResponse

data class MaximumCommentResponse(
    val id: String,
    val content: String,
    val writer: MinimumUserResponse,
    val targetPost: MaximumQuestionResponse

)
