package com.chart.chart.domain.schedule.service

import com.chart.chart.infra.neis.util.TimetableUtil
import neiseApi.payload.sche.ScheReturnResponseDayDto
import org.springframework.stereotype.Service

@Service
class TimetableServiceImpl(
    private val timetableUtil: TimetableUtil
): TimetableService {

    override fun getTodayTimetable(): ScheReturnResponseDayDto {
        return timetableUtil.getTodayTimetable()
    }

    override fun getTimetable(start: Int, end: Int): List<ScheReturnResponseDayDto> {
        return timetableUtil.getTimetable(start, end)
    }

}