package com.chart.chart.global.neis

import com.chart.chart.infra.env.property.NeisProperty
import neiseApi.School
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class SchoolConfig(
    private val neisProperty: NeisProperty
) {

    @Bean
    fun getSchool(): School {
        return School(neisProperty.apiKey)
    }
}