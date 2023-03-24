package com.cheesecake.chickenmasala.ui.search

import android.view.LayoutInflater
import com.cheesecake.chickenmasala.databinding.FragmentSearchBinding
import com.cheesecake.chickenmasala.ui.base.BaseFragment

class SearchFragment : BaseFragment<FragmentSearchBinding>() {
    override val bindingInflater: (LayoutInflater) -> FragmentSearchBinding =
        FragmentSearchBinding::inflate
}