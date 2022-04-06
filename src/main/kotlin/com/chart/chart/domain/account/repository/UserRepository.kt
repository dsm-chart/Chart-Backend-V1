package com.chart.chart.domain.account.repository

import com.chart.chart.domain.account.data.entity.User
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface UserRepository: JpaRepository<User, Int> {

    fun findByGithubId(githubId: String): Optional<User>

}