package com.chart.chart.domain.post.repository

import com.chart.chart.domain.account.data.entity.User
import com.chart.chart.domain.post.data.entity.Comment
import com.chart.chart.domain.post.data.entity.Post
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface CommentRepository: JpaRepository<Comment, String> {

    fun findAllByTargetPost(post: Post): List<Comment>
    fun findByIdAndWriter(id: String, writer: User): Optional<Comment>
}