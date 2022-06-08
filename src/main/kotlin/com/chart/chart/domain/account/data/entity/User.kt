package com.chart.chart.domain.account.data.entity

import com.chart.chart.domain.account.data.dto.UserDto
import com.chart.chart.domain.post.data.entity.Post
import com.chart.chart.domain.post.data.entity.Question
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.domain.Persistable
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@EntityListeners(AuditingEntityListener::class)
class User(
    id: Long,
    githubId: String,
    school: School,
    name: String,
    role: Role,
    bio: String?

): Persistable<Long> {

    @Id
    private val id: Long = id

    private val githubId: String = githubId

    @Embedded
    private val school: School = school

    private val name: String = name

    private val role: Role = role

    val bio: String? = bio

    @CreatedDate
    var createdAt: LocalDateTime? = null

    @LastModifiedDate
    var updatedAt: LocalDateTime? = null

    @OneToMany(fetch = FetchType.LAZY)
    private val postList: MutableList<Post> = ArrayList()

    override fun getId(): Long {
        return this.id
    }

    override fun isNew(): Boolean {
        return this.createdAt == null
    }

    fun addPost(post: Post) {
        this.postList.add(post)
    }

    fun toUserDto(): UserDto{
        return UserDto(
            this.id.toString(),
            this.name,
            this.bio,
            this.school,
            this.githubId,
            this.createdAt!!,
            this.updatedAt!!,
            this.postList.stream().filter{ it is Question }.map{ it as Question }.map { it.toQuestionDto() }.toList()
        )
    }

    fun getSchool(): School {
        return this.school
    }

    fun getRole(): Role {
        return this.role
    }


}