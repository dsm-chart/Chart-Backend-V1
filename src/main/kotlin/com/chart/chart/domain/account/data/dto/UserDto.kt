package com.chart.chart.domain.account.data.dto

import com.chart.chart.domain.account.data.entity.School
import com.chart.chart.domain.account.data.response.MaximumUserResponse
import com.chart.chart.domain.account.data.response.MinimumUserResponse
import java.time.LocalDateTime

data class UserDto(
    val id: String,
    val name: String,
    val bio: String?,
    val school: School,
    val githubId: String,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime

) {

    fun toMaximumUserResponse(): MaximumUserResponse {
        return MaximumUserResponse(
            this.name,
            this.id,
            this.bio,
            this.githubId,
            this.school,
            this.createdAt,
            this.updatedAt
        )
    }

    fun toMinimumUserResponse(): MinimumUserResponse {
        return MinimumUserResponse(
            this.githubId,
            this.name,
            this.bio
        )
    }

}
