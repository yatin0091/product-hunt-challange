package com.yatin.producthunt.presentation.profile

import android.os.Parcelable
import com.yatin.producthunt.domain.entities.Maker
import kotlinx.parcelize.Parcelize

@Parcelize
data class MakerView(
    val id: String,
    val isMaker: Boolean,
    val username: String,
    val name: String,
    val profileImage: String
) : Parcelable

fun Maker.toMakerView() = MakerView(
    id, isMaker, username, name, profileImage
)
