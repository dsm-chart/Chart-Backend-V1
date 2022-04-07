package com.chart.chart.domain.account.controller

import com.chart.chart.domain.account.data.request.LoginRequest
import com.chart.chart.domain.account.data.request.SignupRequest
import com.chart.chart.domain.account.data.response.MaximumUserResponse
import com.chart.chart.domain.account.service.AccountService
import com.chart.chart.global.security.data.request.TokenRequest
import com.chart.chart.global.security.data.response.TokenResponse
import com.chart.chart.infra.github.utils.GithubOAuthUtil
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/api/v1/auth")
class AccountController(
    private val accountService: AccountService

) {

    @PostMapping("/login")
    fun githubOAuthLogin(request: LoginRequest): TokenResponse {
        return accountService.login(request)
    }

    @PostMapping("/signup")
    fun githubOAuthSignup(request: SignupRequest): TokenResponse {
        return accountService.signup(request)
    }

    @GetMapping("/my")
    fun getMyInfo(): MaximumUserResponse {
        return accountService.getMyInfo()
    }

    @PostMapping("/reissue")
    fun reissueToken(@RequestBody request: TokenRequest): TokenResponse {
        return accountService.reissue(request)
    }



}