package com.yatin.producthunt.presentation.postdetail

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import com.yatin.producthunt.R
import com.yatin.producthunt.databinding.FragmentPostDetailBinding
import com.yatin.producthunt.domain.core.Failure
import com.yatin.producthunt.presentation.core.BaseFragment
import com.yatin.producthunt.presentation.core.extension.close
import com.yatin.producthunt.presentation.core.extension.failure
import com.yatin.producthunt.presentation.core.extension.loadGifFromUrl
import com.yatin.producthunt.presentation.core.extension.observe
import com.yatin.producthunt.presentation.core.navigation.Navigator
import com.yatin.producthunt.presentation.home.PostView
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class PostDetailFragment :
    BaseFragment<FragmentPostDetailBinding>(FragmentPostDetailBinding::inflate) {

    companion object {
        private const val PARAM_POST = "param_post"

        fun forPost(postView: PostView?) = PostDetailFragment().apply {
            arguments = bundleOf(PARAM_POST to postView)
        }
    }

    @Inject
    lateinit var navigator: Navigator

    private val postDetailViewModel: PostDetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        with(postDetailViewModel) {
            observe(postDetail, ::renderMovieDetails)
            failure(failure, ::handleFailure)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        postDetailViewModel.loadPostDetail((arguments?.get(PARAM_POST) as PostView).id)
    }

    private fun renderMovieDetails(postDetailView: PostDetailView?) {
        postDetailView?.let {
            binding.userImage.loadGifFromUrl(it.thumbnail)
            binding.name.text = it.name
            binding.tagline.text = it.tagline
            binding.description.text = it.description
            binding.voteCount.text = it.votesCount.toString()
            binding.makers.text = it.makers.joinToString()
            binding.userImage.setOnClickListener {
                navigator.showMakerProfile(requireActivity(), postDetailView.makers[0])
            }
        }
    }

    private fun handleFailure(failure: Failure?) {
        when (failure) {
            is Failure.NetworkConnection -> {
                notify(R.string.failure_network_connection); close()
            }
            is Failure.ServerError -> {
                notify(R.string.failure_server_error); close()
            }
            else -> {
                notify(R.string.failure_server_error); close()
            }
        }
    }

}