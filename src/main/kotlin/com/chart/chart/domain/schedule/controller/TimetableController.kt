package com.chart.chart.domain.schedule.controller

import com.chart.chart.domain.schedule.service.TimetableService
import neiseApi.payload.sche.ScheReturnResponseDayDto
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/api/v1/timetable")
class TimetableController(
    private val timetableService: TimetableService
) {

    @GetMapping("/test")
    fun test(): String {
        return "test"
    }

    @GetMapping("/today")
    fun getTodayTimetable(): ScheReturnResponseDayDto {
        return timetableService.getTodayTimetable()
    }

    @GetMapping
    fun getTimetable(@RequestParam start: Int, end: Int): List<ScheReturnResponseDayDto> {
        return timetableService.getTimetable(start, end)
    }

}