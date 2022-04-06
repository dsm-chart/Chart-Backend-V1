package com.chart.chart.domain.cafeteria.controller

import com.chart.chart.domain.cafeteria.service.CafeteriaService
import com.leeseojune.neisapi.dto.Meal
import neiseApi.payload.sche.ScheReturnResponseDayDto
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/api/v1/meal")
class CafeteriaController(
    private val cafeteriaService: CafeteriaService
) {

    @GetMapping
    fun getMeal(@RequestParam date: String): Meal {
        return cafeteriaService.getMeal(date)
    }

}