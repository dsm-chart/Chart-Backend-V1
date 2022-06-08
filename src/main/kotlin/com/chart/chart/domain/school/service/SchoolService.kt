package com.chart.chart.domain.school.service

import com.chart.chart.domain.school.dto.SchoolSearchDto
import com.chart.chart.domain.school.dto.SchoolSearchResponse
import neiseApi.payload.schoolInfo.SchoolShorten

interface SchoolService {

    fun searchSchool(schoolName: String): List<SchoolShorten>
}