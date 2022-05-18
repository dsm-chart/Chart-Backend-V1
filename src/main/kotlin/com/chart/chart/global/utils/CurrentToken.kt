package com.chart.chart.global.utils

import com.chart.chart.domain.account.data.entity.User
import com.chart.chart.domain.account.exception.UserNotFoundException
import com.chart.chart.domain.account.repository.UserRepository
import com.chart.chart.global.security.exception.NeedTokenException
import org.springframework.stereotype.Component
import org.springframework.security.core.context.SecurityContextHolder


@Component
class CurrentToken(
    private val userRepository: UserRepository
) {

    fun getTokenCredentials(): Int {
        try {
            return SecurityContextHolder.getContext().authentication.credentials.toString().toInt()
        } catch (e: java.lang.Exception) {
            throw NeedTokenException()
        }
    }

    fun getUser(): User {
        return userRepository.findById(getTokenCredentials()).orElse(null)?: throw UserNotFoundException(getTokenCredentials().toString())
    }

}