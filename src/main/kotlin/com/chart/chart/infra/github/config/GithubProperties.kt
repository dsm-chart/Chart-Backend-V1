package com.chart.chart.infra.github.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding


@ConstructorBinding
@ConfigurationProperties("github")
data class GithubProperties (
    private val clientId: String,
    private val clientSecret: String,
    private val accessTokenUrl: String,
    private val userInfoUrl: String
) {

    fun getClientId(): String {
        return this.clientId
    }

    fun getClientSecret(): String {
        return this.clientSecret
    }

    fun getAccessTokenUrl(): String {
        return this.accessTokenUrl
    }

    fun getUserInfoUrl(): String {
        return this.userInfoUrl
    }

}