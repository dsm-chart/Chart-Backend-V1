package com.chart.chart.domain.account.service

import com.chart.chart.domain.account.data.request.LoginRequest
import com.chart.chart.domain.account.data.request.SignupRequest
import com.chart.chart.domain.account.data.response.MaximumUserResponse
import com.chart.chart.global.security.data.request.TokenRequest
import com.chart.chart.global.security.data.response.TokenResponse

interface AccountService {

    fun signup(request: SignupRequest): TokenResponse
    fun login(request: LoginRequest): TokenResponse
    fun reissue(request: TokenRequest): TokenResponse

    fun getMyInfo():  MaximumUserResponse

}