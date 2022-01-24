package com.yatin.producthunt.presentation.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.yatin.producthunt.domain.core.BaseUseCase
import com.yatin.producthunt.domain.entities.Post
import com.yatin.producthunt.domain.usecase.GetPosts
import com.yatin.producthunt.presentation.core.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getPosts: GetPosts) : BaseViewModel() {

    private val _posts: MutableLiveData<List<PostView>> = MutableLiveData()
    val posts: LiveData<List<PostView>> = _posts

    fun loadPosts() =
        getPosts(BaseUseCase.None(), viewModelScope) { it.fold(::handleFailure, ::handlePostList) }

    private fun handlePostList(posts: List<Post>) {
        _posts.value = posts.map { it.toPostView() }
    }
}