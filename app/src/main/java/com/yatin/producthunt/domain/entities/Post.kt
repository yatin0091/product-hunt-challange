package com.yatin.producthunt.domain.entities

import com.yatin.producthunt.presentation.home.PostView

data class Post(val id: String, val name: String,
                val thumbnail: Thumbnail, val tagline: String, val votesCount: Int, val makers : List<Maker>)
