package com.yatin.producthunt.presentation.home

import android.os.Parcelable
import com.yatin.producthunt.domain.entities.Post
import kotlinx.parcelize.Parcelize

@Parcelize
data class PostView(
    val id: String, val name: String,
    val thumbnail: String, val tagline: String, val votesCount: Int, val makers: Array<String>
) :
    Parcelable

fun Post.toPostView() =
    PostView(
        id = id,
        name = name,
        thumbnail = thumbnail.url,
        tagline = tagline,
        votesCount = votesCount,
        makers = makers.map { it.username }.toTypedArray()
    )

