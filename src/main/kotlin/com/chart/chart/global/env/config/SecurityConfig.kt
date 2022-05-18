package com.chart.chart.global.env.config

import com.chart.chart.global.security.filter.JwtExceptionHandlerFilter
import com.chart.chart.global.security.filter.JwtFilter
import com.chart.chart.global.security.jwt.AccessTokenUtils
import com.chart.chart.global.security.service.CustomUserDetailsService
import com.fasterxml.jackson.databind.ObjectMapper
import org.hibernate.internal.FilterConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter


@Configuration
@EnableWebSecurity
class SecurityConfig(
    private val jwtUtils: AccessTokenUtils,
    private val customUserDetailsService: CustomUserDetailsService,
    private val objectMapper: ObjectMapper
): WebSecurityConfigurerAdapter() {


    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }

    @Throws(Exception::class)
    override fun configure(web: WebSecurity) {
        web.ignoring().antMatchers("/swagger-ui/**", "/v3/api-docs/**", "/api-docs/**", "/api-docs.json"
            , "/swagger-ui.html", "/api-docs/**"
        )
    }

    @Throws(Exception::class)
    override fun configure(httpSecurity: HttpSecurity) {
        httpSecurity
            .csrf().disable()
            .cors()

            .and()
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)


            .and()
            .authorizeRequests()
            .antMatchers(
                "/api/**").permitAll()
            .antMatchers("/api/auth/code").hasRole("ADMIN")
            .anyRequest().denyAll()

            .and()
            .apply(FilterConfiguration(jwtUtils, customUserDetailsService, objectMapper))

    }

}