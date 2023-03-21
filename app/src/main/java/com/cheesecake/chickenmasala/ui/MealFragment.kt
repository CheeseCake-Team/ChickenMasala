package com.cheesecake.chickenmasala.ui

import android.view.LayoutInflater
import com.cheesecake.chickenmasala.databinding.FragmentMealBinding

class MealFragment : BaseFragment<FragmentMealBinding>() {
    override val bindingInflater: (LayoutInflater) -> FragmentMealBinding =
        FragmentMealBinding::inflate


}