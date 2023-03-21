package com.cheesecake.chickenmasala.ui

import android.view.LayoutInflater
import com.cheesecake.chickenmasala.databinding.FragmentHomeBinding

class HomeFragment : BaseFragment<FragmentHomeBinding>() {
    override val bindingInflater: (LayoutInflater) -> FragmentHomeBinding =
        FragmentHomeBinding::inflate


}