package com.yatin.producthunt.presentation.postdetail

import android.os.Parcelable
import com.yatin.producthunt.domain.entities.Maker
import com.yatin.producthunt.domain.entities.Post
import com.yatin.producthunt.domain.entities.PostDetail
import com.yatin.producthunt.domain.entities.Thumbnail
import com.yatin.producthunt.presentation.home.PostView
import kotlinx.parcelize.Parcelize

@Parcelize
data class PostDetailView(
    val id: String, val name: String,
    val thumbnail: String, val tagline: String, val description: String,
    val votesCount: Int, val makers: Array<String>
): Parcelable


fun PostDetail.toPostDetailView() =
    PostDetailView(
        id = id,
        name = name,
        thumbnail = thumbnail.url,
        tagline = tagline,
        votesCount = votesCount,
        description = description,
        makers = makers.map { it.username }.toTypedArray()
    )