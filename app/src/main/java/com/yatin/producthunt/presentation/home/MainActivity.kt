package com.yatin.producthunt.presentation.home

import android.content.Context
import android.content.Intent
import com.yatin.producthunt.presentation.core.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity() {
    companion object {
        fun callingIntent(context: Context) = Intent(context, MainActivity::class.java)
    }

    override fun fragment() = HomeFragment()
}