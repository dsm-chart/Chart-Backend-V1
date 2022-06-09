package com.chart.chart.global.security.service

import com.chart.chart.domain.account.exception.UserNotFoundException
import com.chart.chart.domain.account.repository.UserRepository
import com.chart.chart.global.security.data.CustomUserDetails
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Component

@Component
class CustomUserDetailsService(
    private var userRepository: UserRepository
) : UserDetailsService {


    override fun loadUserByUsername(username: String): CustomUserDetails {
        return userRepository.findById(username.toLong()).map { CustomUserDetails() }
            .orElse(null) ?: throw UserNotFoundException(username)
    }
}