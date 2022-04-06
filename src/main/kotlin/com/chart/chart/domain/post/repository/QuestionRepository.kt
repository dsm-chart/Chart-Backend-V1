package com.chart.chart.domain.post.repository

import com.chart.chart.domain.post.data.entity.Question
import org.springframework.data.jpa.repository.JpaRepository

interface QuestionRepository: JpaRepository<Question, String> {
}