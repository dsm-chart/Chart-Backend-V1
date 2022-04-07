package com.chart.chart.domain.account.repository

import com.chart.chart.domain.account.data.entity.RefreshToken
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.CrudRepository

interface RefreshTokenRepository: CrudRepository<RefreshToken, String> {

}
