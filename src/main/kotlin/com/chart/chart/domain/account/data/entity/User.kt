package com.chart.chart.domain.account.data.entity

import com.chart.chart.domain.account.data.dto.UserDto
import com.chart.chart.domain.post.data.entity.Post
import com.chart.chart.domain.post.data.entity.Question
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDateTime
import javax.persistence.*

@Entity
class User(
    id: Int,
    githubId: String,
    school: School,
    name: String,
    role: Role,
    bio: String?

) {

    @Id
    private val id: Int = id

    private val githubId: String = githubId

    @Embedded
    private val school: School = school

    private val name: String = name

    private val role: Role = role

    private val bio: String? = bio

    @CreationTimestamp
    private val createdAt: LocalDateTime? = null

    @UpdateTimestamp
    private val updatedAt: LocalDateTime? = null

    @OneToMany(fetch = FetchType.LAZY)
    private val postList: MutableList<Post> = ArrayList()

    fun getId(): String {
        return this.id.toString()
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