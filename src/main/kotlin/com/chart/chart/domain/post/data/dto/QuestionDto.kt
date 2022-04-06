package com.chart.chart.domain.post.data.dto

import com.chart.chart.domain.account.data.entity.User
import com.chart.chart.domain.post.data.response.MaximumQuestionResponse

data class QuestionDto(
    val id: String,
    val title: String,
    val content: String,
    val writer: User

) {

    fun toMaximumQuestionResponse(): MaximumQuestionResponse {
        return MaximumQuestionResponse(
            this.id,
            this.title,
            this.content,
            this.writer.toUserDto().toMinimumUserResponse()
        )
    }

}
