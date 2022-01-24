package com.yatin.producthunt.model.remote

import android.content.Context
import android.content.res.AssetManager

import com.google.gson.Gson

import com.google.gson.reflect.TypeToken
import com.yatin.producthunt.domain.entities.Maker
import com.yatin.producthunt.domain.entities.Post
import com.yatin.producthunt.domain.entities.PostDetail
import com.yatin.producthunt.domain.entities.toPostDetail
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject


class NetworkService @Inject constructor(@ApplicationContext private val context: Context) {

    private fun AssetManager.readAssetsFile(fileName: String): String =
        open(fileName).bufferedReader().use { it.readText() }

    fun getPostList(): List<Post> {
        val postJson = context.assets.readAssetsFile("PostsList.json")
        val postType = object : TypeToken<List<Post>>() {}.type
        return Gson().fromJson(postJson, postType)
    }

    fun getPostDetail(postId: String): PostDetail? {
        val postList = getPostList()
        return (postList.find { it.id == postId })?.toPostDetail()
    }

    fun getMakerDetail(makerId: String): Maker {
        val makerJson = context.assets.readAssetsFile("MakerProfile.json")
        val makerType = object : TypeToken<Maker>() {}.type
        return Gson().fromJson(makerJson, makerType)
    }
}