package com.yatin.producthunt.domain.api

import com.yatin.producthunt.domain.core.Either
import com.yatin.producthunt.domain.core.Failure
import com.yatin.producthunt.domain.entities.Maker

interface MakerApi {
    fun makerDetail(makerId: String) : Either<Failure, Maker>
}