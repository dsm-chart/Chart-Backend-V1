package com.chart.chart.infra.github.utils

import com.chart.chart.domain.account.data.entity.Role
import com.chart.chart.domain.account.data.entity.School
import com.chart.chart.domain.account.data.entity.User
import com.chart.chart.infra.github.config.GithubProperties
import com.chart.chart.infra.github.data.dto.NewGithubUserInfoResponse
import com.chart.chart.infra.github.data.dto.RepoGithubUserInfoResponse
import com.chart.chart.infra.github.data.request.GithubAccessTokenRequest
import com.chart.chart.infra.github.exception.GithubUnAuthorizeException
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.http.*
import org.springframework.stereotype.Component
import org.springframework.web.client.HttpClientErrorException
import org.springframework.web.client.RestTemplate
import org.springframework.web.util.NestedServletException
import java.net.URI


@Component
class GithubOAuthUtil(
    private val prop: GithubProperties

) {

    private val restTemplate = RestTemplate()
    private val PREFIX = "Bearer "
    private val url: URI = URI(prop.getAccessTokenUrl())

    fun requestAccessTokenWithGithubCode(code: String): String {
        val response = restTemplate.postForEntity(
            url,
            GithubAccessTokenRequest(
                prop.getClientId(),
                prop.getClientSecret(),
                code
            ),
            String::class.java
        )

        val res: String = (response.body).toString()
        val token = res.substring(res.indexOf("=") + 1, res.indexOf("&"))
        return token
    }

    fun getUserInfoByAccessToken(accessToken: String): User {
        val url = URI(prop.getUserInfoUrl())
        val headers = HttpHeaders()
        val auth = PREFIX + accessToken
        headers.set("Authorization", auth)
        val entity: HttpEntity<String> = HttpEntity<String>("", headers)
        var response = Any()
        try {
            response = restTemplate.exchange(url, HttpMethod.GET, entity, String::class.java)
        } catch (e: HttpClientErrorException) {
            println(e.message)
            throw GithubUnAuthorizeException(accessToken)
        }

        val mapper = ObjectMapper()
        try {
            val userInfo = mapper.readValue(response.body, NewGithubUserInfoResponse::class.java)

            return User(
                (userInfo.id),
                userInfo.login,
                userInfo.name,
                Role.COMMON,
                userInfo.bio
            )
        } catch (e: Exception) {
            val userInfo = mapper.readValue(response.body, RepoGithubUserInfoResponse::class.java)

            return User(
                (userInfo.id),
                userInfo.login,
                userInfo.name,
                Role.COMMON,
                userInfo.bio
            )
        }
    }

}