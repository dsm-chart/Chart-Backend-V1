package com.chart.chart.domain.post.controller

import com.chart.chart.domain.post.data.request.CreateQuestionRequest
import com.chart.chart.domain.post.data.request.EditQuestionRequest
import com.chart.chart.domain.post.data.response.MaximumQuestionListResponse
import com.chart.chart.domain.post.data.response.MaximumQuestionResponse
import com.chart.chart.domain.post.service.QuestionService
import org.springframework.data.domain.Page
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/api/v1/question")
class QuestionController(
    private val questionService: QuestionService
) {

    @GetMapping("/{id}")
    fun getQuestion(@PathVariable id: String): MaximumQuestionResponse {
        return questionService.getQuestion(id)
    }

    @GetMapping("/list")
    fun getQuestionList(@RequestParam idx: Int, @RequestParam size: Int): Page<MaximumQuestionResponse> {
        return questionService.getQuestionList(idx, size)
    }

    @GetMapping("/my")
    fun getMyQuestionList(@RequestParam idx: Int, @RequestParam size: Int): Page<MaximumQuestionResponse> {
        return questionService.getMyQuestionList(idx, size)
    }

    @PostMapping
    fun createQuestion(@RequestBody request: CreateQuestionRequest) {
        return questionService.createQuestion(request)
    }

    @PatchMapping
    fun editQuestion(@RequestBody request: EditQuestionRequest) {
        return questionService.editQuestion(request)
    }

    @DeleteMapping
    fun deleteQuestion(@RequestParam id: String) {
        return questionService.deleteQuestion(id)
    }




}