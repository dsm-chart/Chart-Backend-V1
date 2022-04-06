package com.chart.chart.global.security.jwt

import com.chart.chart.global.env.property.JwtProperty
import io.jsonwebtoken.Claims
import org.springframework.stereotype.Component
import java.time.LocalDateTime

@Component
class RefreshTokenUtils(
    private val jwtConfig: JwtProperty
): StandardJwtUtils<String>(jwtConfig) {

    override fun getClaims(data: String): Map<String, String> {
        val map: MutableMap<String, String> = HashMap()
        map[jwtConfig.mapKey] = data
        return map
    }

    override fun getExpiredAt(now: LocalDateTime): LocalDateTime {
        return LocalDateTime.now().plusSeconds(jwtConfig.refreshExpiredAt)
    }

    override fun getDataFromClaims(claims: Claims): String {
        return claims.get(jwtConfig.mapKey, String::class.java)
    }


}