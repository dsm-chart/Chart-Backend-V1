package com.chart.chart.global.security.jwt

interface JwtUtils<T> {

    fun encode(data: T): String
    fun decode(token: String): T

}