package com.chart.chart.domain.post.data.entity

import com.chart.chart.domain.account.data.entity.User
import com.chart.chart.domain.post.data.dto.QuestionDto
import com.chart.chart.domain.post.data.response.MaximumQuestionResponse
import javax.persistence.DiscriminatorValue
import javax.persistence.Entity


@Entity
@DiscriminatorValue("question")
class Question(
    id: String,
    title: String,
    content: String,
    writer: User
): Post(
    id,
    content,
    writer

) {

    private var title: String = title


    fun toQuestionDto(): QuestionDto {
        return QuestionDto(
            this.getId(),
            this.title,
            this.getContent(),
            this.getWriter()
        )
    }

    fun editQuestion(title: String, content: String): Question {
        this.title = title
        this.setContent(content)
        return this
    }

    fun toMaximumQuestionResponse(): MaximumQuestionResponse {
        return MaximumQuestionResponse(
            this.getId(),
            this.title,
            this.getContent(),
            this.getWriter().toUserDto().toMinimumUserResponse()
        )
    }
}