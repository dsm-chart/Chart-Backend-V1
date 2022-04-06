package com.chart.chart.global.env.config

import com.chart.chart.global.security.filter.JwtFilter
import com.chart.chart.global.security.jwt.AccessTokenUtils
import com.chart.chart.global.security.service.CustomUserDetailsService
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

@EnableWebSecurity
@Configuration
class SecurityConfig(
    private val jwtUtils: AccessTokenUtils,
    private val customUserDetailsService: CustomUserDetailsService
): WebSecurityConfigurerAdapter() {


    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }

    @Throws(Exception::class)
    override fun configure(web: WebSecurity) {
        web.ignoring().antMatchers("/static/css/**, /static/js/**, *.ico")
        web.ignoring().antMatchers(
            "/v3/api-docs", "/configuration/ui", "/swagger-resources",
            "/configuration/security", "/swagger-ui.html", "/webjars/**", "/swagger/**", "/api/auth/admin"
        )
    }


    @Throws(Exception::class)
    override fun configure(httpSecurity: HttpSecurity) {
        httpSecurity
            .csrf().disable()
            .cors().disable()
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .authorizeRequests()
            .antMatchers(
                "/api/**").permitAll()
            .antMatchers("/api/auth/code").hasRole("ADMIN")
            .anyRequest().permitAll()
            .and()
            .addFilterBefore(JwtFilter(jwtUtils, customUserDetailsService), UsernamePasswordAuthenticationFilter::class.java)
    }

}