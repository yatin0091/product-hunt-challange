package com.yatin.producthunt.domain.entities

data class PostDetail(
    val id: String, val name: String,
    val thumbnail: Thumbnail, val tagline: String, val description: String,
    val votesCount: Int, val makers: List<Maker>
)

fun Post.toPostDetail() =
    PostDetail(
        id = id,
        name = name,
        thumbnail = thumbnail,
        tagline = tagline,
        description = "A Notion collection of all the community curated resources one would need to plan, start & launch or scale an Ecommerce business - be it Twitter threads, books, apps, influencers, agencies, etc that you need in quest of building an ecommerce or D2C business",
        votesCount = votesCount,
        makers = makers
    )
