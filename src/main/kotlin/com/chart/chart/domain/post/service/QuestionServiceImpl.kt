package com.chart.chart.domain.post.service

import com.chart.chart.domain.account.data.response.MinimumUserResponse
import com.chart.chart.domain.post.data.entity.Question
import com.chart.chart.domain.post.data.request.CreateQuestionRequest
import com.chart.chart.domain.post.data.request.EditQuestionRequest
import com.chart.chart.domain.post.data.response.MaximumQuestionResponse
import com.chart.chart.domain.post.exception.IsNotPostWriterException
import com.chart.chart.domain.post.exception.PostNotFoundException
import com.chart.chart.domain.post.repository.QuestionRepository
import com.chart.chart.global.utils.CurrentToken
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import java.util.*


@Service
class QuestionServiceImpl(
    private val questionRepository: QuestionRepository,
    private val current: CurrentToken

): QuestionService {

    override fun getQuestion(id: String): MaximumQuestionResponse {
        val quest = questionRepository.findById(id).orElse(null)?: throw PostNotFoundException(id)
        return quest.toQuestionDto().toMaximumQuestionResponse()
    }

    override fun getQuestionList(idx: Int, size: Int): List<MaximumQuestionResponse> {
        val questList = questionRepository.findAll(PageRequest.of(idx, size))
        if (questList.isEmpty) throw PostNotFoundException("Any Data Not Exists")
        return questList.stream()
            .map { it.toQuestionDto().toMaximumQuestionResponse() }
            .toList()
    }

    override fun createQuestion(request: CreateQuestionRequest) {
        questionRepository.save(
            Question(
                UUID.randomUUID().toString(),
                request.title,
                request.content,
                current.getUser()
            )
        )
    }

    override fun editQuestion(request: EditQuestionRequest) {
        val question = questionRepository.findById(request.id).orElse(null)?: throw PostNotFoundException(request.id)

        questionRepository.save(
            question.editQuestion(request.title, request.content)
        )

    }

    override fun deleteQuestion(id: String) {
        val question = questionRepository.findById(id).orElse(null) ?: throw PostNotFoundException(id)
        val user = current.getUser()
        if (question.getWriter().equals(user)) throw IsNotPostWriterException(user.getId())
        questionRepository.delete(question)
    }


}