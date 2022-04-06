package com.chart.chart.domain.post.service

import com.chart.chart.domain.post.data.entity.Comment
import com.chart.chart.domain.post.data.request.EditCommentRequest
import com.chart.chart.domain.post.data.request.ReplyCommentRequest
import com.chart.chart.domain.post.data.response.MaximumCommentResponse
import com.chart.chart.domain.post.data.response.MinimumCommentResponse
import com.chart.chart.domain.post.exception.AnyCommentNotExistsException
import com.chart.chart.domain.post.exception.CommentNotExistsException
import com.chart.chart.domain.post.exception.PostNotFoundException
import com.chart.chart.domain.post.repository.CommentRepository
import com.chart.chart.domain.post.repository.QuestionRepository
import com.chart.chart.global.utils.CurrentToken
import org.springframework.stereotype.Service
import java.util.*


@Service
class CommentServiceImpl(
    private val commentRepository: CommentRepository,
    private val questionRepository: QuestionRepository,
    private val current: CurrentToken
): CommentService {

    override fun getComment(commentId: String): MaximumCommentResponse {
        val comment = commentRepository.findById(commentId).orElse(null)?: throw CommentNotExistsException(commentId)
        return comment.toMaximumCommentResponse()
    }

    override fun getCommentList(targetPostId: String): List<MinimumCommentResponse> {
        val targetPost = questionRepository.findById(targetPostId).orElse(null)?: throw PostNotFoundException(targetPostId)
        val commentList = commentRepository.findAllByTargetPost(targetPost)
        if (commentList.isEmpty) throw AnyCommentNotExistsException("NULL")
        return commentList.stream().map { it.toMinimumCommentResponse() }.toList()
    }

    override fun replyComment(request: ReplyCommentRequest) {
        val target = questionRepository.findById(request.targetId)
            .orElse(null)?:throw PostNotFoundException(request.targetId)
        commentRepository.save(
            Comment(
                UUID.randomUUID().toString(),
                request.content,
                current.getUser(),
                target
            )
        )

    }

    override fun editComment(request: EditCommentRequest) {
        val comment = commentRepository.findByIdAndWriter(request.commentId, current.getUser())
            .orElse(null)?: throw PostNotFoundException(request.commentId)

        comment.editComment(request.content)
        commentRepository.save(
            comment
        )
    }

    override fun deleteComment(commentId: String) {
        val comment = commentRepository.findByIdAndWriter(commentId, current.getUser())
            .orElse(null)?: throw PostNotFoundException(commentId)
        commentRepository.delete(comment)
    }
}