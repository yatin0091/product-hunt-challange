package com.yatin.producthunt.presentation.core.extension

import android.content.Context
import android.view.View
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.yatin.producthunt.databinding.ActivityMainBinding
import com.yatin.producthunt.presentation.core.BaseActivity
import com.yatin.producthunt.presentation.core.BaseFragment
import com.yatin.producthunt.presentation.home.MainActivity

//TODO delete this class
inline fun FragmentManager.inTransaction(func: FragmentTransaction.() -> FragmentTransaction) =
    beginTransaction().func().commit()

fun BaseFragment<*>.close() = fragmentManager?.popBackStack()

val BaseFragment<*>.viewContainer: View get() = (activity as BaseActivity).binding.fragmentContainer

val BaseFragment<*>.appContext: Context get() = activity?.applicationContext!!
