package com.yatin.producthunt.domain.api

import com.yatin.producthunt.domain.core.Either
import com.yatin.producthunt.domain.core.Failure
import com.yatin.producthunt.domain.entities.Post
import com.yatin.producthunt.domain.entities.PostDetail

interface PostApi {
    fun posts(): Either<Failure, List<Post>>
    fun postDetails(postId: String): Either<Failure, PostDetail>
}