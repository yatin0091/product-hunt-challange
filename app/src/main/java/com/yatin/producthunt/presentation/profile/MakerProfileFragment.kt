package com.yatin.producthunt.presentation.profile

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import com.yatin.producthunt.R
import com.yatin.producthunt.databinding.FragmentMakerProfileBinding
import com.yatin.producthunt.domain.core.Failure
import com.yatin.producthunt.presentation.core.BaseFragment
import com.yatin.producthunt.presentation.core.extension.close
import com.yatin.producthunt.presentation.core.extension.failure
import com.yatin.producthunt.presentation.core.extension.loadFromUrl
import com.yatin.producthunt.presentation.core.extension.observe
import com.yatin.producthunt.presentation.core.navigation.Navigator
import com.yatin.producthunt.presentation.home.PostView
import com.yatin.producthunt.presentation.postdetail.PostDetailFragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MakerProfileFragment :
    BaseFragment<FragmentMakerProfileBinding>(FragmentMakerProfileBinding::inflate) {

    companion object {
        private const val PARAM_MAKER_ID = "param_maker_id"

        fun forMakerProfile(makerId: String?) = MakerProfileFragment().apply {
            arguments = bundleOf(PARAM_MAKER_ID to makerId)
        }
    }

    @Inject
    lateinit var navigator: Navigator

    private val makerProfileViewModel: MakerProfileViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        with(makerProfileViewModel) {
            observe(makerDetail, ::renderMakerDetails)
            failure(failure, ::handleFailure)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        makerProfileViewModel.loadMakerDetail(arguments?.get(PARAM_MAKER_ID) as String)
    }

    private fun renderMakerDetails(makerView: MakerView?) {
        makerView?.let {
            binding.avatar.loadFromUrl(it.profileImage)
            binding.name.text = it.name
            binding.username.text = it.username
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