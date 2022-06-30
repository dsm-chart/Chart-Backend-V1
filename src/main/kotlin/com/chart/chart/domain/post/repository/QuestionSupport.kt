package com.chart.chart.domain.post.repository

import com.chart.chart.domain.post.data.entity.Question

interface QuestionSupport {

    fun getQuestionList(): List<Question>
}