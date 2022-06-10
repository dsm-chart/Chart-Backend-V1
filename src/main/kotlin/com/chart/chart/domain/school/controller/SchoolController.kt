package com.chart.chart.domain.school.controller

import com.chart.chart.domain.school.service.SchoolService
import neiseApi.payload.schoolInfo.SchoolShorten
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/school")
class SchoolController(
    private val schoolService: SchoolService
) {

    @PostMapping
    fun searchSchool(@RequestParam school: String): List<SchoolShorten> {
        return schoolService.searchSchool(school)
    }



}