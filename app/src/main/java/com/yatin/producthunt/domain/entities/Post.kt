package com.yatin.producthunt.domain.entities

data class Post(
    val id: String, val name: String,
    val thumbnail: Thumbnail, val tagline: String, val votesCount: Int, val makers: List<Maker>
)
