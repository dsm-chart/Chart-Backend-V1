package com.chart.chart.domain.account.service

import com.chart.chart.domain.account.data.entity.Role
import com.chart.chart.domain.account.data.entity.School
import com.chart.chart.domain.account.data.entity.User
import com.chart.chart.domain.account.data.request.LoginRequest
import com.chart.chart.domain.account.data.request.SignupRequest
import com.chart.chart.domain.account.data.response.MaximumUserResponse
import com.chart.chart.domain.account.exception.UserNotFoundException
import com.chart.chart.domain.account.repository.UserRepository
import com.chart.chart.global.exception.UserAlreadyExistsException
import com.chart.chart.global.security.data.response.TokenResponse
import com.chart.chart.global.security.jwt.AccessTokenUtils
import com.chart.chart.global.security.jwt.RefreshTokenUtils
import com.chart.chart.global.utils.CurrentToken
import com.chart.chart.infra.github.utils.GithubOAuthUtil
import org.springframework.stereotype.Service

@Service
class AccountServiceImpl(
    private val gitUtil: GithubOAuthUtil,
    private val accessTokenUtils: AccessTokenUtils,
    private val refreshTokenUtils: RefreshTokenUtils,
    private val userRepository: UserRepository,
    private val current: CurrentToken

): AccountService {

    override fun signup(request: SignupRequest): TokenResponse {
        val userInfo = gitUtil.getUserInfoByAccessToken(
            gitUtil.requestGithubCode(request.githubCode)
        )
        if (userRepository.findById(userInfo.id).isPresent) throw UserAlreadyExistsException(userInfo.login)

        userRepository.save(
            User(
                userInfo.id,
                userInfo.login,
                School(
                    request.schoolCode,
                    request.areaCode,
                    request.grade,
                    request.classNum
                ),
                userInfo.name,
                Role.COMMON,
                userInfo.bio
            )
        )

        return provideToken(userInfo.id.toString())
    }

    override fun login(request: LoginRequest): TokenResponse {
        val userInfo = gitUtil.getUserInfoByAccessToken(
            gitUtil.requestGithubCode(request.githubCode)
        )
        val optionalUser = userRepository.findById(userInfo.id)
        if (optionalUser.isPresent) return provideToken(optionalUser.get().getId())
        throw UserNotFoundException(userInfo.id.toString())
    }

    override fun getMyInfo(): MaximumUserResponse {
        return current.getUser().toUserDto().toMaximumUserResponse()
    }

    private fun provideToken(data: String): TokenResponse {
        return TokenResponse(
            accessTokenUtils.encode(data),
            refreshTokenUtils.encode(data)
        )
    }

}