package com.cheesecake.chickenmasala.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.cheesecake.chickenmasala.databinding.FragmentMealsBinding
import com.cheesecake.chickenmasala.databinding.ItemMealCardBinding

class MealsFragment : BaseFragment<FragmentMealsBinding>() {
    override val bindingInflater: (LayoutInflater) -> FragmentMealsBinding =
        FragmentMealsBinding::inflate


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fillLinerLayoutWithCardItem()
    }

    private fun fillLinerLayoutWithCardItem() {
        for (i in 0 until 20) {
            val cardItemView = ItemMealCardBinding.inflate(layoutInflater)
            binding.linear.addView(cardItemView.view2)
        }
    }
}