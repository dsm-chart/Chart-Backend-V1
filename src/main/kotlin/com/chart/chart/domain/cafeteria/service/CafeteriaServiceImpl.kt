package com.chart.chart.domain.cafeteria.service

import com.chart.chart.infra.neis.util.MealUtil
import com.leeseojune.neisapi.dto.Meal
import org.springframework.stereotype.Service


@Service
class CafeteriaServiceImpl(
    private val mealUtil: MealUtil
): CafeteriaService {

    override fun getMeal(date: String): Meal {
        return  mealUtil.getMeal(date)
    }

}