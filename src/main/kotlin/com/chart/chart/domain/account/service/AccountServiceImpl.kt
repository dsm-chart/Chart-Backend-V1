package com.chart.chart.domain.account.service

import com.chart.chart.domain.account.data.entity.Role
import com.chart.chart.domain.account.data.entity.School
import com.chart.chart.domain.account.data.entity.User
import com.chart.chart.domain.account.data.request.CheckGithubIdRequest
import com.chart.chart.domain.account.data.request.LoginRequest
import com.chart.chart.domain.account.data.request.SignupRequest
import com.chart.chart.domain.account.data.response.MaximumUserResponse
import com.chart.chart.domain.account.exception.UserNotFoundException
import com.chart.chart.domain.account.repository.RefreshTokenRepository
import com.chart.chart.domain.account.repository.UserRepository
import com.chart.chart.global.exception.UserAlreadyExistsException
import com.chart.chart.global.security.data.request.TokenRequest
import com.chart.chart.global.security.data.response.TokenResponse
import com.chart.chart.global.security.exception.InvalidTokenException
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
    private val current: CurrentToken,
    private val refreshTokenRepository: RefreshTokenRepository

): AccountService {

    private val REFRESH_EXPIRED = 120960000

    override fun signup(request: SignupRequest): TokenResponse {
        val userInfo = gitUtil.getUserInfoByAccessToken(
            request.accessToken
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
//        if (request.githubCode.equals("111")) return provideToken(userRepository.findById(111).get().getId())
        val userInfo = gitUtil.getUserInfoByAccessToken(
            request.accessToken
//            gitUtil.requestGithubCode(request.githubCode)
        )
        val optionalUser = userRepository.findById(userInfo.id)
        if (optionalUser.isPresent) return provideToken(optionalUser.get().getId())
        throw UserNotFoundException(userInfo.id.toString())
    }

    override fun reissue(request: TokenRequest): TokenResponse {
        val userPk = accessTokenUtils.decode(request.accessToken).toInt()

        val user = userRepository.findById(userPk).orElse(null)?: throw UserNotFoundException(userPk.toString())
        val tokenResponse = provideToken(user.getId())
        resetRefreshToken(tokenResponse.refreshToken, userPk.toString())

        return tokenResponse
    }

    override fun getMyInfo(): MaximumUserResponse {
        return current.getUser().toUserDto().toMaximumUserResponse()
    }

    override fun checkGithubToken(request: CheckGithubIdRequest): Boolean {
        val userInfo = gitUtil.getUserInfoByAccessToken(
            request.accessToken
        )
        userRepository.findById(userInfo.id).orElse(null)?: return true
        return false
    }

    private fun resetRefreshToken(token: String, userPk: String) {
        val refreshToken = refreshTokenRepository.findById(userPk)
            .orElse(null)?: throw InvalidTokenException(token)

        refreshToken.resetTokenExpiration(REFRESH_EXPIRED)

        refreshTokenRepository.save(refreshToken)
    }


    private fun provideToken(data: String): TokenResponse {
        return TokenResponse(
            accessTokenUtils.encode(data),
            refreshTokenUtils.encode(data)
        )
    }

}