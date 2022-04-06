package com.chart.chart.domain.cafeteria.service

import com.leeseojune.neisapi.dto.Meal
import neiseApi.payload.sche.ScheReturnResponseDayDto


interface CafeteriaService {

    fun getMeal(meal: String): Meal

}