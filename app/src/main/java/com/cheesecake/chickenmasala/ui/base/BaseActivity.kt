package com.cheesecake.chickenmasala.ui.base

import android.os.Bundle
import android.os.PersistableBundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.viewbinding.ViewBinding

abstract class BaseActivity<VB:ViewBinding>:AppCompatActivity() {

    protected abstract val bindingInflater: (LayoutInflater) -> VB
    private var _binding: VB? = null
    protected val binding: VB get() = _binding ?: throw IllegalStateException("ViewBinding must not be null")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        _binding = bindingInflater(layoutInflater)
        setContentView(binding.root)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}