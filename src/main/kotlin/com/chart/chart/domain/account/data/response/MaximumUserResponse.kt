package com.chart.chart.domain.account.data.response

import com.chart.chart.domain.account.data.entity.School
import com.chart.chart.domain.post.data.response.MaximumQuestionResponse
import com.chart.chart.domain.post.data.response.MinimumQuestionResponse
import java.time.LocalDateTime

data class MaximumUserResponse(
    val name: String,
    val id: String,
    val bio: String?,
    val githubId: String,
    val school: School,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime,
    val questionList: List<MinimumQuestionResponse>

)
