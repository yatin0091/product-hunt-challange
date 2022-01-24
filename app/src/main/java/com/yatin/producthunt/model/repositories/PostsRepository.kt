package com.yatin.producthunt.model.repositories

import com.yatin.producthunt.domain.api.PostApi
import com.yatin.producthunt.domain.core.Either
import com.yatin.producthunt.domain.core.Failure
import com.yatin.producthunt.domain.entities.Post
import com.yatin.producthunt.domain.entities.PostDetail
import com.yatin.producthunt.model.core.BaseRepository
import com.yatin.producthunt.model.remote.NetworkHandler
import com.yatin.producthunt.model.remote.NetworkService
import javax.inject.Inject

class PostsRepository @Inject constructor(
    private val networkHandler: NetworkHandler,
    private val networkService: NetworkService
) : PostApi, BaseRepository() {

    override fun posts(): Either<Failure, List<Post>> {
        return when (networkHandler.isNetworkAvailable()) {
            true -> Either.Right(networkService.getPostList())
            false -> Either.Left(Failure.NetworkConnection)
        }
    }


    override fun postDetails(postId: String): Either<Failure, PostDetail> {
        return when (networkHandler.isNetworkAvailable()) {
            true -> {
                val postDetail = networkService.getPostDetail(postId)
                return when (postDetail != null) {
                    true -> Either.Right(postDetail)
                    false -> Either.Left(Failure.ServerError)
                }
            }
            false -> Either.Left(Failure.NetworkConnection)
        }
    }
}