package com.chart.chart.infra.env.property

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding


@ConstructorBinding
@ConfigurationProperties("neis")
data class NeisProperty(
    val apiKey: String

)
