package com.chart.chart.domain.account.data.entity

import org.springframework.data.redis.core.RedisHash
import org.springframework.data.redis.core.TimeToLive
import org.springframework.data.redis.core.index.Indexed
import javax.persistence.Id


@RedisHash
class RefreshToken(
    id: String,
    token: String,
    ttl: Int
) {

    @Id
    private val id: String = id
    @Indexed
    private var token: String = token
    @TimeToLive
    private var ttl = ttl

    fun getRefreshToken(): String {
        return this.token
    }

    fun resetTokenExpiration(ttl: Int) {
        this.ttl = ttl
    }

}