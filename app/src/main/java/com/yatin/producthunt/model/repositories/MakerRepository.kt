package com.yatin.producthunt.model.repositories

import com.yatin.producthunt.domain.api.MakerApi
import com.yatin.producthunt.domain.core.Either
import com.yatin.producthunt.domain.core.Failure
import com.yatin.producthunt.domain.entities.Maker
import com.yatin.producthunt.model.core.BaseRepository
import com.yatin.producthunt.model.remote.NetworkHandler
import com.yatin.producthunt.model.remote.NetworkService
import javax.inject.Inject

class MakerRepository @Inject constructor(
    private val networkHandler: NetworkHandler,
    private val networkService: NetworkService) : MakerApi, BaseRepository() {

    override fun makerDetail(makerId: String): Either<Failure, Maker> {
        return when (networkHandler.isNetworkAvailable()) {
            true -> Either.Right(networkService.getMakerDetail(makerId))
            false -> Either.Left(Failure.NetworkConnection)
        }
    }
}