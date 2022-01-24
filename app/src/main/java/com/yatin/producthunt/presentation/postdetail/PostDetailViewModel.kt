package com.yatin.producthunt.presentation.postdetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.yatin.producthunt.domain.entities.PostDetail
import com.yatin.producthunt.domain.usecase.GetPostDetail
import com.yatin.producthunt.presentation.core.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PostDetailViewModel @Inject constructor(
    private val getPostDetail: GetPostDetail
) : BaseViewModel() {

    private val _postDetail: MutableLiveData<PostDetailView> = MutableLiveData()
    val postDetail: LiveData<PostDetailView> = _postDetail

    fun loadPostDetail(postId: String) =
        getPostDetail(GetPostDetail.Params(postId), viewModelScope) {
            it.fold(
                ::handleFailure,
                ::handlePostDetail
            )
        }

    private fun handlePostDetail(postDetail: PostDetail) {
        _postDetail.value = postDetail.toPostDetailView()
    }
}