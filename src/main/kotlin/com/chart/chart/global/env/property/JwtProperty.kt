package com.chart.chart.global.env.property

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding


@ConstructorBinding
@ConfigurationProperties("jwt")
data class JwtProperty(
    val secretKey: String,
    val accessExpiredAt: Long,
    val refreshExpiredAt: Long,
    val mapKey: String

)
