package com.chart.chart.global.security.jwt

import com.chart.chart.global.env.property.JwtProperty
import io.jsonwebtoken.Claims
import org.springframework.stereotype.Component
import java.time.LocalDateTime


@Component
class AccessTokenUtils(
    private val jwtProperty: JwtProperty
): StandardJwtUtils<String>(jwtProperty) {


    override fun getClaims(data: String): Map<String, String> {
        val map: MutableMap<String, String> = HashMap()
        map[jwtProperty.secretKey] = data
        return map
    }

    override fun getExpiredAt(now: LocalDateTime): LocalDateTime {
        return LocalDateTime.now().plusSeconds(jwtProperty.accessExpiredAt)
    }

    override fun getDataFromClaims(claims: Claims): String {
        return claims.get(jwtProperty.secretKey, String::class.java)
    }


}