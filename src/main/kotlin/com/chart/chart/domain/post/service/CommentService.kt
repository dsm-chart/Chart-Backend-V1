package com.chart.chart.domain.post.service

import com.chart.chart.domain.post.data.request.EditCommentRequest
import com.chart.chart.domain.post.data.request.ReplyCommentRequest
import com.chart.chart.domain.post.data.response.MaximumCommentResponse
import com.chart.chart.domain.post.data.response.MinimumCommentResponse

interface CommentService {

    fun getComment(commentId: String): MaximumCommentResponse
    fun getCommentList(targetPostId: String): List<MinimumCommentResponse>
    fun replyComment(request: ReplyCommentRequest)
    fun editComment(request: EditCommentRequest)
    fun deleteComment(commentId: String)


}