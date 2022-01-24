package com.yatin.producthunt.presentation.core.navigation

import android.content.Context
import android.view.View
import androidx.fragment.app.FragmentActivity
import com.yatin.producthunt.domain.entities.Post
import com.yatin.producthunt.presentation.home.MainActivity
import com.yatin.producthunt.presentation.home.PostView
import com.yatin.producthunt.presentation.postdetail.PostDetailActivity
import com.yatin.producthunt.presentation.profile.MakerProfileActivity
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class Navigator @Inject constructor() {

    private fun showPosts(context: Context) =
        context.startActivity(MainActivity.callingIntent(context))

    fun showPostDetails(activity: FragmentActivity, postView: PostView) {
        val intent = PostDetailActivity.callingIntent(activity, postView)
        activity.startActivity(intent)
    }

    fun showMakerProfile(activity: FragmentActivity, makerId: String) {
        val intent = MakerProfileActivity.callingIntent(activity, makerId)
        activity.startActivity(intent)
    }
}


