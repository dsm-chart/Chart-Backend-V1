package com.chart.chart.global.env.config

import com.chart.chart.global.security.filter.JwtExceptionHandlerFilter
import com.chart.chart.global.security.filter.JwtFilter
import com.chart.chart.global.security.jwt.AccessTokenUtils
import com.chart.chart.global.security.service.CustomUserDetailsService
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.security.config.annotation.SecurityConfigurerAdapter
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.web.DefaultSecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter


class FilterConfiguration(
    private val jwtUtils: AccessTokenUtils,
    private val customUserDetailsService: CustomUserDetailsService,
    private val objectMapper: ObjectMapper
): SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity>() {

    override fun configure(builder: HttpSecurity) {
        val jwtFilter = JwtFilter(jwtUtils, customUserDetailsService)
        val exceptionFilter = JwtExceptionHandlerFilter(objectMapper)
        builder.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter::class.java)
        builder.addFilterBefore(exceptionFilter, JwtFilter::class.java)
    }

}