package com.cheesecake.chickenmasala.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.cheesecake.chickenmasala.R
import com.cheesecake.chickenmasala.databinding.FragmentHomeBinding

class HomeFragment : BaseFragment<FragmentHomeBinding>() {
    override val bindingInflater: (LayoutInflater) -> FragmentHomeBinding =
        FragmentHomeBinding::inflate


}