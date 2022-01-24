package com.yatin.producthunt.presentation.core

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.yatin.producthunt.R
import com.yatin.producthunt.databinding.ActivityMainBinding
import com.yatin.producthunt.presentation.core.extension.inTransaction

/**
 * Base Activity class with helper methods for handling fragment transactions and back button
 * events.
 *
 * @see AppCompatActivity
 */
abstract class BaseActivity : AppCompatActivity() {

    internal lateinit var binding: ActivityMainBinding

    abstract fun fragment(): BaseFragment<*>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        addFragment(savedInstanceState)
    }

    override fun onBackPressed() {
        (supportFragmentManager.findFragmentById(R.id.fragmentContainer) as BaseFragment<*>).onBackPressed()
        super.onBackPressed()
    }

    private fun addFragment(savedInstanceState: Bundle?) =
        savedInstanceState ?: supportFragmentManager.inTransaction {
            add(
                R.id.fragmentContainer,
                fragment()
            )
        }
}
