package com.chart.chart.domain.account.data.dto

import com.chart.chart.domain.account.data.entity.School
import com.chart.chart.domain.account.data.response.MaximumUserResponse
import com.chart.chart.domain.account.data.response.MinimumUserResponse
import com.chart.chart.domain.post.data.dto.QuestionDto
import com.chart.chart.domain.post.data.entity.Post
import java.time.LocalDateTime

data class UserDto(
    val id: String,
    val name: String,
    val bio: String?,
    val school: School,
    val githubId: String,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime,
    val questionList: List<QuestionDto>

) {

    fun toMaximumUserResponse(): MaximumUserResponse {
        return MaximumUserResponse(
            this.name,
            this.id,
            this.bio,
            this.githubId,
            this.school,
            this.createdAt,
            this.updatedAt,
            this.questionList.stream().map { it.toMinimumQuestionResponse() }.toList()
        )
    }

    fun toMinimumUserResponse(): MinimumUserResponse {
        return MinimumUserResponse(
            this.githubId,
            this.name,
            this.bio
        )
    }

}
