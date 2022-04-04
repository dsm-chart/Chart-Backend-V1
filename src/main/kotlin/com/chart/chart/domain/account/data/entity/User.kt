package com.chart.chart.domain.account.data.entity

import javax.persistence.Entity
import javax.persistence.Id

@Entity
class User(
    id: String,
    email: String


) {

    @Id
    private val id: String = id



}