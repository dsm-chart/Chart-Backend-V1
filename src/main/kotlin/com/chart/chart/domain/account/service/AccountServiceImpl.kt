package com.chart.chart.domain.account.service

import com.chart.chart.domain.account.data.entity.RefreshToken
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
import org.springframework.transaction.annotation.Transactional

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

    override fun signup(request: SignupRequest) {
        val user = gitUtil.getUserInfoByAccessToken(
            request.accessToken
        )
        if (userRepository.findById(user.id).isPresent) throw UserAlreadyExistsException(user.githubId)
        user.setSchool(request)

        userRepository.save(user)
    }

    @Transactional
    override fun signupWithToken(request: SignupRequest) {
        var token = gitUtil.requestAccessTokenWithGithubCode(request.accessToken)
        val user: User = gitUtil.getUserInfoByAccessToken(token)
        if (userRepository.findById(user.id).isPresent) throw UserAlreadyExistsException(user.githubId)
        user.setSchool(request)

        userRepository.save(user)
    }

    override fun login(request: LoginRequest): TokenResponse {
//        if (request.githubCode.equals("111")) return provideToken(userRepository.findById(111).get().getId())
        val userInfo = gitUtil.getUserInfoByAccessToken(
            request.accessToken
//            gitUtil.requestGithubCode(request.githubCode)
        )
        val optionalUser = userRepository.findById(userInfo.id)
        if (optionalUser.isPresent) return provideToken(optionalUser.get().id.toString())
        throw UserNotFoundException(userInfo.id.toString())
    }

    override fun reissue(request: TokenRequest): TokenResponse {
        val userPk = accessTokenUtils.decode(request.accessToken).toLong()

        val user = userRepository.findById(userPk).orElse(null)?: throw UserNotFoundException(userPk.toString())
        val tokenResponse = provideToken(user.id.toString())
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
        userRepository.findById(userInfo.id).orElse(null)?: return false
        return true
    }

    private fun resetRefreshToken(token: String, userPk: String) {
        val refreshToken = refreshTokenRepository.findById(userPk)
            .orElse(null)?: throw InvalidTokenException(userPk)

        refreshToken.resetTokenExpiration(token, REFRESH_EXPIRED)

        refreshTokenRepository.save(refreshToken)
    }


    private fun provideToken(data: String): TokenResponse {
        val refreshToken = refreshTokenUtils.encode(data)
        refreshTokenRepository.save(
            RefreshToken(
                data,
                refreshToken,
                REFRESH_EXPIRED
            )
        )
        return TokenResponse(
            accessTokenUtils.encode(data),
            refreshToken
        )
    }

}