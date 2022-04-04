package com.chart.chart

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication

@SpringBootApplication
@ConfigurationPropertiesScan
class ChartApplication

fun main(args: Array<String>) {
    runApplication<ChartApplication>(*args)
}
