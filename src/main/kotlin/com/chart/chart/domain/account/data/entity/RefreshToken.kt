package com.chart.chart.domain.account.data.entity

import org.springframework.data.annotation.Id
import org.springframework.data.redis.core.RedisHash
import org.springframework.data.redis.core.TimeToLive
import org.springframework.data.redis.core.index.Indexed


@RedisHash
class RefreshToken(
    id: String,
    token: String,
    ttl: Int
) {

    @Id
    val id: String = id
    @Indexed
    var token: String = token
    @TimeToLive
    var ttl = ttl

    fun getRefreshToken(): String {
        return this.token
    }

    fun resetTokenExpiration(token: String, ttl: Int) {
        this.token = token
        this.ttl = ttl
    }

}