package com.yatin.producthunt.presentation.postdetail

import android.content.Context
import android.content.Intent
import com.yatin.producthunt.presentation.core.BaseActivity
import com.yatin.producthunt.presentation.home.PostView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PostDetailActivity : BaseActivity() {

    companion object {
        private const val INTENT_EXTRA_PARAM_POST = "com.yatin.producthunt.INTENT_PARAM_POST"

        fun callingIntent(context: Context, postView: PostView) =
            Intent(context, PostDetailActivity::class.java).apply {
                putExtra(INTENT_EXTRA_PARAM_POST, postView)
            }
    }

    override fun fragment() =
        PostDetailFragment.forPost(intent.getParcelableExtra(INTENT_EXTRA_PARAM_POST))
}