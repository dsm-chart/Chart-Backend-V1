package com.chart.chart.global.security.filter

import com.chart.chart.global.security.exception.InvalidTokenException
import com.chart.chart.global.exception.base.response.ErrorResponse
import com.fasterxml.jackson.databind.ObjectMapper
import io.jsonwebtoken.JwtException
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class JwtExceptionHandlerFilter(
    private val objectMapper: ObjectMapper
): OncePerRequestFilter() {


    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        try {
            filterChain.doFilter(request, response)
        } catch (e: InvalidTokenException){
            response.status = e.errorCode.status.value()
            response.contentType = MediaType.APPLICATION_JSON_VALUE
            response.characterEncoding = "UTF-8"
            objectMapper.writeValue(response.writer, ErrorResponse(
                e.errorCode.message,
                e.errorCode.status,
                "TokenException"
            ))
        } catch (e: JwtException) {
            response.status = HttpStatus.BAD_REQUEST.value()
            response.contentType = MediaType.APPLICATION_JSON_VALUE
            response.characterEncoding = "UTF-8"
            objectMapper.writeValue(response.writer, ErrorResponse(
                    e.message!!,
                    HttpStatus.BAD_REQUEST,
                "TokenException"
            ))
        }


    }


}