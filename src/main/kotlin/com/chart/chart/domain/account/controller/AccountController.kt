package com.chart.chart.domain.account.controller

import com.chart.chart.domain.account.data.request.CheckGithubIdRequest
import com.chart.chart.domain.account.data.request.LoginRequest
import com.chart.chart.domain.account.data.request.SignupRequest
import com.chart.chart.domain.account.data.response.CheckGithubIdResponse
import com.chart.chart.domain.account.data.response.MaximumUserResponse
import com.chart.chart.domain.account.service.AccountService
import com.chart.chart.global.security.data.request.TokenRequest
import com.chart.chart.global.security.data.response.TokenResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/api/v1/auth")
class AccountController(
    private val accountService: AccountService

) {

    @PostMapping("/login")
    fun githubOAuthLogin(@RequestBody request: LoginRequest): TokenResponse {
        return accountService.login(request)
    }

    @PostMapping("/signup")
    fun githubOAuthSignup(@RequestBody request: SignupRequest): TokenResponse {
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

    @PutMapping("/check")
    fun checkOauth(@RequestBody request: CheckGithubIdRequest): CheckGithubIdResponse {
        return CheckGithubIdResponse(
            accountService.checkGithubToken(request)
        )
    }


    @PostMapping("/test")
    fun test(): String {
        return "Test"
    }




}