package com.chart.chart.infra.fcm.util

import com.chart.chart.domain.account.repository.UserRepository
import com.chart.chart.global.utils.CurrentToken
import org.springframework.stereotype.Component


@Component
class FcmUtil(
    private val current: CurrentToken,
    private val userRepository: UserRepository
) {





}