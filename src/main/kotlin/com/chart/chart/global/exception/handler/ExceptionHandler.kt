package com.chart.chart.global.exception.handler

import com.chart.chart.global.exception.base.GlobalException
import com.chart.chart.global.exception.base.response.ErrorResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.RestControllerAdvice


@RestController
@RestControllerAdvice
class ExceptionHandler {


    @ExceptionHandler(GlobalException::class)
    fun GlobalException(exception: GlobalException): ResponseEntity<*> {
        return ResponseEntity.status(exception.errorCode.status).body(
            ErrorResponse(
                exception.errorCode.message,
                exception.errorCode.status,
                exception.data
            )
        )
    }


}