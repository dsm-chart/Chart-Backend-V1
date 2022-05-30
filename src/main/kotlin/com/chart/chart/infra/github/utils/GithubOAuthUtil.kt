package com.chart.chart.infra.github.utils

import com.chart.chart.infra.github.config.GithubProperties
import com.chart.chart.infra.github.data.dto.LegacyGithubUserInfoResponse
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.http.*
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate
import java.net.URI


@Component
class GithubOAuthUtil(
    private val prop: GithubProperties

) {

    private val restTemplate = RestTemplate()
    private val PREFIX = "Bearer "
    private val url: URI = URI(prop.getAccessTokenUrl())

//    fun requestGithubCode(code: String): String {
//
//        println("CLIENTID: ${prop.getClientId()} \n SECRET: ${prop.getClientSecret()}\n CODE: $code\n")
//        val response = restTemplate.postForEntity(url,
//            GithubAccessTokenRequest(
//                prop.getClientId(),
//                prop.getClientSecret(),
//                code
//            ),
//            String::class.java
//        )
//        println(response)
//
//        val res: String = (response.body).toString()
//        println(res)
//        return res.substring(res.indexOf("=") + 1, res.indexOf("&"))
//    }

    fun getUserInfoByAccessToken(accessToken: String): LegacyGithubUserInfoResponse {
        val url = URI(prop.getUserInfoUrl())
        val headers = HttpHeaders()
        headers.set("Authorization", PREFIX + accessToken)
        val entity: HttpEntity<String> = HttpEntity<String>("", headers)
        val response = restTemplate.exchange(url, HttpMethod.GET, entity, String::class.java)
        println(response)
        val mapper = ObjectMapper()
        val resp = mapper.readValue(response.body, LegacyGithubUserInfoResponse::class.java)

        return resp
    }

}