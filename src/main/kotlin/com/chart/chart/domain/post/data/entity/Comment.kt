package com.chart.chart.domain.post.data.entity

import com.chart.chart.domain.account.data.entity.User
import com.chart.chart.domain.post.data.response.MaximumCommentResponse
import com.chart.chart.domain.post.data.response.MinimumCommentResponse
import javax.persistence.DiscriminatorValue
import javax.persistence.Entity
import javax.persistence.ManyToOne


@Entity
@DiscriminatorValue("reply")
class Comment(
    id: String,
    content: String,
    writer: User,
    target: Post

): Post(
    id,
    content,
    writer

) {

    @ManyToOne
    val targetPost: Post = target

    fun toMaximumCommentResponse(): MaximumCommentResponse {
        return MaximumCommentResponse(
            this.getId(),
            this.getContent(),
            this.getWriter().toUserDto().toMaximumUserResponse(),
            (this.targetPost as Question).toMaximumQuestionResponse()
        )
    }

    fun toMinimumCommentResponse(): MinimumCommentResponse {
        return MinimumCommentResponse(
            this.getContent(),
            this.getWriter().toUserDto().toMinimumUserResponse()
        )
    }

    fun editComment(content: String) {
        this.setContent(content)
    }


}