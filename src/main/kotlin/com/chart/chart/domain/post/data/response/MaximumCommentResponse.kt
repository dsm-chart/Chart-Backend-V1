package com.chart.chart.domain.post.data.response

import com.chart.chart.domain.account.data.response.MaximumUserResponse

data class MaximumCommentResponse(
    val id: String,
    val content: String,
    val writer: MaximumUserResponse,
    val targetPost: MaximumQuestionResponse

)
