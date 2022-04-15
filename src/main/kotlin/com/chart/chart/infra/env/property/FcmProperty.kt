package com.chart.chart.infra.env.property

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConstructorBinding
@ConfigurationProperties("fcm")
data class FcmProperty(
    val firebaseCreateScoped: String,
    val configPath: String
)
