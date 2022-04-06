package com.chart.chart.domain.account.data.entity

import javax.persistence.Column
import javax.persistence.Embeddable


@Embeddable
class School(
    schoolCode: String,
    areaCode: String,
    grade: Int,
    classNum: Int
) {
    @Column(name = "ATPT_OFCDC_SC_CODE")
    val ATPT_OFCDC_SC_CODE: String = areaCode
    @Column(name = "SD_SCHUL_CODE")
    val SD_SCHUL_CODE: String = schoolCode
    @Column(name = "grade")
    val grade: Int = grade
    @Column(name = "class_num")
    val classNum: Int = classNum


}