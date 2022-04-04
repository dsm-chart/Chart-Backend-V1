package com.chart.chart.domain.account.controller

import com.chart.chart.infra.github.utils.GithubOAuthUtil
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/api/v1/auth")
class AccountController(
    private val githubOAuthUtil: GithubOAuthUtil

) {


    @GetMapping("/test")
    fun getAccessTokenTest(@RequestParam code: String): String {
//        return githubOAuthUtil.getAccessToken(code)
    }



}