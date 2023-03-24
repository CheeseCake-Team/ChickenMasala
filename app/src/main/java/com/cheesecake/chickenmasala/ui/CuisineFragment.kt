package com.cheesecake.chickenmasala.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import com.cheesecake.chickenmasala.databinding.FragmentCuisineBinding
import com.cheesecake.chickenmasala.databinding.ItemCuisineBinding


class CuisineFragment : BaseFragment<FragmentCuisineBinding>() {
    override val bindingInflater: (LayoutInflater) -> FragmentCuisineBinding =
        FragmentCuisineBinding::inflate

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fillLinearLayoutWithCard()
    }

    private fun fillLinearLayoutWithCard() {
        for (recipe in 0..20) {
            val itemCuisineBinding = ItemCuisineBinding.inflate(layoutInflater)
            binding.cuisineContainer.addView(itemCuisineBinding.root)
        }
    }
}