package com.chart.chart.global.security.data

import com.chart.chart.domain.account.data.entity.User
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class CustomUserDetails: UserDetails {

    private val user: User? = null

    override fun getAuthorities(): Collection<GrantedAuthority?> {
        val grantedAuthority = GrantedAuthority { user?.getRole()?.name }
        return setOf(grantedAuthority)
    }

    override fun getPassword(): String? {
        return null
    }

    override fun getUsername(): String? {
        return null
    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun isEnabled(): Boolean {
        return true
    }
}