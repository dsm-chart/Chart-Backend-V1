package com.chart.chart.global.security.jwt

import com.chart.chart.global.env.property.JwtProperty
import com.chart.chart.global.security.exception.InvalidTokenException
import io.jsonwebtoken.Claims
import io.jsonwebtoken.JwtException
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import java.sql.Timestamp
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*

abstract class StandardJwtUtils<T>(
    private val jwtConfig: JwtProperty
): JwtUtils<T> {
    override fun encode(data: T): String {
        return Jwts.builder()
            .setClaims(getClaims(data))
            .setExpiration(Timestamp.valueOf(getExpiredAt(LocalDateTime.now())))
            .signWith(SignatureAlgorithm.HS256, getSecretKey())
            .setIssuedAt(Timestamp.valueOf(LocalDateTime.now()))
            .compact()
    }

    override fun decode(token: String): T {
        try {
            val data = Jwts.parser()
                .setSigningKey(getSecretKey())
                .parseClaimsJws(token)
                .body
            return getDataFromClaims(data)
        } catch (e: JwtException) {
            throw InvalidTokenException(e.message!!)
        }
    }


    protected abstract fun getClaims(data: T): Map<String, String>

    protected abstract fun getExpiredAt(now: LocalDateTime): LocalDateTime

    protected abstract fun getDataFromClaims(claims: Claims): T

    fun getSecretKey(): String {
        return jwtConfig.secretKey
    }

}