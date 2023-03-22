package com.cheesecake.chickenmasala.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

abstract class BaseFragment<viewBinding : ViewBinding> : Fragment() {

    abstract val bindingInflater: (LayoutInflater) -> viewBinding
    private var _binding: viewBinding? = null
    protected var binding = _binding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = bindingInflater.invoke(layoutInflater)
        return _binding!!.root
    }



}