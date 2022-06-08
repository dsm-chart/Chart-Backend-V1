package com.chart.chart.domain.school.service

import com.chart.chart.domain.school.dto.SchoolSearchDto
import com.chart.chart.domain.school.dto.SchoolSearchResponse
import com.fasterxml.jackson.databind.ObjectMapper
import neiseApi.School
import neiseApi.payload.schoolInfo.SchoolShorten
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpRequest
import org.springframework.http.MediaType
import org.springframework.http.client.ClientHttpRequestExecution
import org.springframework.http.client.ClientHttpRequestInterceptor
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import java.net.URI
import java.net.URL
import java.util.Arrays

@Service
class SchoolServiceImpl(
    private val school: School
): SchoolService {



    override fun searchSchool(schoolName: String): List<SchoolShorten> {
        return school.getSchoolDetailInfo(schoolName)
    }

}