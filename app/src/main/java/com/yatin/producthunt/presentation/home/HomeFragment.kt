package com.yatin.producthunt.presentation.home

import android.os.Bundle
import android.view.View
import androidx.annotation.StringRes
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.yatin.producthunt.R
import com.yatin.producthunt.databinding.FragmentHomeBinding
import com.yatin.producthunt.domain.core.Failure
import com.yatin.producthunt.domain.entities.Post
import com.yatin.producthunt.presentation.core.BaseFragment
import com.yatin.producthunt.presentation.core.extension.*
import com.yatin.producthunt.presentation.core.navigation.Navigator
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {

    @Inject
    lateinit var navigator: Navigator

    @Inject
    lateinit var postsAdapter: PostsAdapter

    private val homeViewModel: HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        with(homeViewModel) {
            observe(posts, ::renderMoviesList)
            failure(failure, ::handleFailure)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeView()
        loadMoviesList()
    }

    private fun initializeView() {
        binding.postList.layoutManager = LinearLayoutManager(appContext)
        binding.postList.adapter = postsAdapter
        postsAdapter.clickListener = { postView ->
            navigator.showPostDetails(requireActivity(), postView)
        }
    }

    private fun loadMoviesList() {
        binding.emptyView.invisible()
        binding.postList.visible()
        homeViewModel.loadPosts()
    }

    private fun renderMoviesList(postViews: List<PostView>?) {
        postsAdapter.collection = postViews.orEmpty()
    }

    private fun handleFailure(failure: Failure?) {
        when (failure) {
            is Failure.NetworkConnection -> renderFailure(R.string.failure_network_connection)
            is Failure.ServerError -> renderFailure(R.string.failure_server_error)
            else -> renderFailure(R.string.failure_server_error)
        }
    }

    private fun renderFailure(@StringRes message: Int) {
        binding.postList.invisible()
        binding.emptyView.visible()
        notifyWithAction(message, R.string.action_refresh, ::loadMoviesList)
    }

}