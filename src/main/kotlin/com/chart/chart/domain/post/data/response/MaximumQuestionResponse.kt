package com.chart.chart.domain.post.data.response

import com.chart.chart.domain.account.data.response.MinimumUserResponse

data class MaximumQuestionResponse(
    val id: String,
    val title: String,
    val content: String,
    val writer: MinimumUserResponse


)
