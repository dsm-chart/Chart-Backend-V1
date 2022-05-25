package com.chart.chart.domain.post.controller

import com.chart.chart.domain.post.data.request.EditCommentRequest
import com.chart.chart.domain.post.data.request.ReplyCommentRequest
import com.chart.chart.domain.post.data.response.MaximumCommentResponse
import com.chart.chart.domain.post.data.response.MinimumCommentListResponse
import com.chart.chart.domain.post.data.response.MinimumCommentResponse
import com.chart.chart.domain.post.service.CommentService
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/api/v1/comment")
class CommentController(
    private val commentService: CommentService
) {

    @GetMapping("/num/{commentId}")
    fun getComment(@PathVariable commentId: String): MaximumCommentResponse {
        return commentService.getComment(commentId)
    }

    @GetMapping("/{postId}")
    fun getCommentList(@PathVariable postId: String): MinimumCommentListResponse {
        return MinimumCommentListResponse(
            commentService.getCommentList(postId)
        )
    }

    @PostMapping
    fun replyComment(@RequestBody request: ReplyCommentRequest) {
        return commentService.replyComment(request)
    }

    @PatchMapping
    fun editComment(@RequestBody request: EditCommentRequest) {
        return commentService.editComment(request)
    }

    @DeleteMapping
    fun deleteComment(@RequestParam commentId: String) {
        return commentService.deleteComment(commentId)
    }


}