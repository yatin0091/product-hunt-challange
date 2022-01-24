package com.yatin.producthunt.domain.usecase

import com.yatin.producthunt.domain.api.PostApi
import com.yatin.producthunt.domain.core.BaseUseCase
import com.yatin.producthunt.domain.entities.Post
import javax.inject.Inject

class GetPosts @Inject constructor(private val postApi: PostApi) :
    BaseUseCase<List<Post>, BaseUseCase.None>() {
    override suspend fun run(params: None) = postApi.posts()
}