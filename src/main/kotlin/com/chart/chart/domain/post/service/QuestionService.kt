package com.chart.chart.domain.post.service


import com.chart.chart.domain.post.data.request.CreateQuestionRequest
import com.chart.chart.domain.post.data.request.EditQuestionRequest
import com.chart.chart.domain.post.data.response.MaximumQuestionResponse

interface QuestionService {

    fun getQuestion(id: String): MaximumQuestionResponse
    fun getQuestionList(idx: Int, size: Int): List<MaximumQuestionResponse>
    fun createQuestion(request: CreateQuestionRequest)
    fun editQuestion(request: EditQuestionRequest)
    fun deleteQuestion(id: String)


}