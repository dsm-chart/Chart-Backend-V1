package com.chart.chart.infra.github.utils

import com.chart.chart.infra.github.config.GithubProperties
import com.chart.chart.infra.github.data.request.GithubAccessTokenRequest
import com.chart.chart.infra.github.data.response.GithubUserInfoResponse
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.util.JSONPObject
import org.springframework.http.*
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate
import org.springframework.web.client.exchange
import org.springframework.web.client.postForEntity
import java.net.HttpURLConnection
import java.net.URI
import java.net.URL


@Component
class GithubOAuthUtil(
    private val prop: GithubProperties

) {

    private val restTemplate = RestTemplate()
    private val PREFIX = "Bearer "

    fun requestGithubCode(code: String): String {

        val url: URI = URI(prop.getAccessTokenUrl())
        val response = restTemplate.postForEntity(url,
            GithubAccessTokenRequest(
                prop.getClientId(),
                prop.getClientSecret(),
                code
            ),
            String::class.java
        )
        val res: String = (response.body).toString()
        return res.substring(res.indexOf("=") + 1, res.indexOf("&"))
    }

    fun getUserInfoByAccessToken(accessToken: String): GithubUserInfoResponse {
        val url = URI(prop.getUserInfoUrl())

        val headers = HttpHeaders()
        headers.set("Authorization", PREFIX + accessToken)
        val entity: HttpEntity<String> = HttpEntity<String>("", headers)
        val response = restTemplate.exchange(url, HttpMethod.GET, entity, String::class.java)

        val mapper = ObjectMapper()
        val resp = mapper.readValue(response.body, GithubUserInfoResponse::class.java)

        return resp
    }

}