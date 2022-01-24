package com.yatin.producthunt.presentation.profile

import android.content.Context
import android.content.Intent
import com.yatin.producthunt.presentation.core.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MakerProfileActivity : BaseActivity() {

    companion object {
        private const val INTENT_EXTRA_PARAM_MAKER = "com.yatin.producthunt.INTENT_PARAM_MAKER"

        fun callingIntent(context: Context, makerId: String) =
            Intent(context, MakerProfileActivity::class.java).apply {
                putExtra(INTENT_EXTRA_PARAM_MAKER, makerId)
            }
    }

    override fun fragment() =
        MakerProfileFragment.forMakerProfile(intent.getStringExtra(INTENT_EXTRA_PARAM_MAKER))
}