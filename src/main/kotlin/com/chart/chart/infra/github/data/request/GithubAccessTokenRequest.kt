package com.chart.chart.infra.github.data.request

import com.fasterxml.jackson.annotation.JsonProperty


data class GithubAccessTokenRequest(
    @JsonProperty("client_id")
    val clientId: String,
    @JsonProperty("client_secret")
    val clientSecret: String,
    @JsonProperty("code")
    val code: String
)
