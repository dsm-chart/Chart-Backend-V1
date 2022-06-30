package com.chart.chart.domain.post.repository

import com.chart.chart.domain.post.data.entity.QQuestion.question
import com.chart.chart.domain.post.data.entity.Question
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.stereotype.Repository

@Repository
class QuestionSupportImpl(
    private val query: JPAQueryFactory
):QuestionSupport {
    override fun getQuestionList(): List<Question> {
        return query.select(question)
            .from(question)
            .limit(10)
            .orderBy(question.createdAt.desc())
            .fetch()
    }
}