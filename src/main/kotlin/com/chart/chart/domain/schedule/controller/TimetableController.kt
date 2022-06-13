package com.chart.chart.domain.schedule.controller

import com.chart.chart.domain.schedule.dto.ScheReturnResponseListDayDto
import com.chart.chart.domain.schedule.service.TimetableService
import neiseApi.payload.sche.ScheReturnResponseDayDto
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


@RestController
@RequestMapping("/api/v1/timetable")
class TimetableController(
    private val timetableService: TimetableService
) {


    @GetMapping("/today")
    fun getTodayTimetable(): ScheReturnResponseDayDto {
        return timetableService.getTodayTimetable()
    }

    @GetMapping("/week")
    fun getWeekTimetable(): ScheReturnResponseListDayDto {
        val today = LocalDate.now()
        val mon = today.with(DayOfWeek.MONDAY).format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        val fri = today.with(DayOfWeek.FRIDAY).format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        return ScheReturnResponseListDayDto(
            timetableService.getTimetable(mon.toInt(), fri.toInt())
        )
    }

    @GetMapping
    fun getTimetable(@RequestParam start: Int, end: Int): ScheReturnResponseListDayDto {
        return ScheReturnResponseListDayDto(
            timetableService.getTimetable(start, end)
        )
    }

}