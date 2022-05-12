package com.chart.chart.domain.post.data.entity

import com.chart.chart.domain.account.data.entity.User
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDateTime
import javax.persistence.*


@DiscriminatorColumn(name = "post_type")
@Entity
abstract class Post(
    id: String,
    content: String,
    writer: User


) {
    @Id
    private val id: String = id

    private var content: String = content

    @ManyToOne(fetch = FetchType.LAZY)
    private val writer: User = writer

    @CreationTimestamp
    private val createdAt: LocalDateTime? = null
    @UpdateTimestamp
    private val updatedAt: LocalDateTime? = null


    fun getId(): String {
        return this.id
    }

    fun getContent(): String {
        return this.content
    }

    protected fun setContent(content: String) {
        this.content = content
    }

    fun getWriter(): User {
        return this.writer
    }
}
