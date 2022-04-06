package com.chart.chart.domain.cafeteria.exception

import com.chart.chart.global.exception.base.type.ErrorCode
import com.chart.chart.global.exception.base.GlobalException


class MealException(data: String): GlobalException(data, ErrorCode.MEAL_EXCEPTION) {
}
