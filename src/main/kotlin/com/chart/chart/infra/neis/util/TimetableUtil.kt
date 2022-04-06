package com.chart.chart.infra.neis.util

import com.chart.chart.domain.cafeteria.exception.MealException
import com.chart.chart.global.utils.CurrentToken
import com.chart.chart.infra.env.property.NeisProperty
import neiseApi.School
import neiseApi.payload.sche.ScheReturnResponseDayDto
import org.springframework.stereotype.Component
import java.io.IOException
import java.time.LocalDate
import java.time.format.DateTimeFormatter


@Component
class TimetableUtil(
    private val neisProperty: NeisProperty,
    private val current: CurrentToken
) {

    fun getTodayTimetable(): ScheReturnResponseDayDto {
        val today = Integer.valueOf(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd")))
        return getTimetable(today, today).get(0)
    }

    fun getTimetable(start: Int, end: Int): List<ScheReturnResponseDayDto> {
        val school: School = School(neisProperty.apiKey)
        val user = current.getUser()
        try {
            return school.getSchoolSchedule(
                user.getSchool().SD_SCHUL_CODE, user.getSchool().grade, user.getSchool().classNum, start, end
            )
        } catch (e: IOException) {
            throw MealException("Can't Bring Meal!")
        }
    }

}