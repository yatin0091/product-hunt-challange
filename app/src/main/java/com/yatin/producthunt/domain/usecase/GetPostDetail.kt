package com.yatin.producthunt.domain.usecase

import com.yatin.producthunt.domain.api.PostApi
import com.yatin.producthunt.domain.core.BaseUseCase
import com.yatin.producthunt.domain.entities.PostDetail
import javax.inject.Inject

class GetPostDetail @Inject constructor(private val postApi: PostApi) :
    BaseUseCase<PostDetail, GetPostDetail.Params>() {
    override suspend fun run(params: Params) = postApi.postDetails(params.id)

    data class Params(val id: String)
}
