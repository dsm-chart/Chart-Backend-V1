package com.chart.chart.domain.schedule.service

import neiseApi.payload.sche.ScheReturnResponseDayDto

interface TimetableService {

    fun getTodayTimetable(): ScheReturnResponseDayDto
    fun getTimetable(start: Int, end: Int): List<ScheReturnResponseDayDto>

}