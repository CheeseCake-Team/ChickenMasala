package com.cheesecake.chickenmasala.ui.categories

import android.view.LayoutInflater
import com.cheesecake.chickenmasala.databinding.FragmentCategoriesBinding
import com.cheesecake.chickenmasala.ui.base.BaseFragment

class CategoriesFragment : BaseFragment<FragmentCategoriesBinding>() {
    override val bindingInflater: (LayoutInflater) -> FragmentCategoriesBinding =
        FragmentCategoriesBinding::inflate

}