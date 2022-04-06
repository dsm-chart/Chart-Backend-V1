package com.chart.chart.infra.neis.util

import com.chart.chart.global.utils.CurrentToken
import com.chart.chart.infra.env.property.NeisProperty
import com.leeseojune.neisapi.NeisApi
import com.leeseojune.neisapi.dto.Meal
import org.springframework.stereotype.Component
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Component
class MealUtil(
    private val current: CurrentToken
) {
    private val neisApi = NeisApi.Builder().build()

    fun getMeal(date: String): Meal {

        return neisApi.getMealsByAbsoluteDay(date
            , current.getUser().getSchool().ATPT_OFCDC_SC_CODE,
            current.getUser().getSchool().SD_SCHUL_CODE)
    }
}