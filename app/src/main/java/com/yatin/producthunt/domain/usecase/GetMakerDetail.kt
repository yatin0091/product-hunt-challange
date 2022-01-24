package com.yatin.producthunt.domain.usecase

import com.yatin.producthunt.domain.api.MakerApi
import com.yatin.producthunt.domain.core.BaseUseCase
import com.yatin.producthunt.domain.entities.Maker
import com.yatin.producthunt.domain.entities.Post
import javax.inject.Inject

class GetMakerDetail @Inject constructor(private val makerApi: MakerApi) :
    BaseUseCase<Maker, GetMakerDetail.Params>() {
    override suspend fun run(params: Params) = makerApi.makerDetail(params.id)

    data class Params(val id: String)

}