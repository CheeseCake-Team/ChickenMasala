package com.cheesecake.chickenmasala.ui

import android.view.LayoutInflater
import com.cheesecake.chickenmasala.databinding.FragmentHomeBinding
import com.cheesecake.chickenmasala.databinding.ItemRecipesBinding

class HomeFragment : BaseFragment<FragmentHomeBinding>() {
    override val bindingInflater: (LayoutInflater) -> FragmentHomeBinding =
        FragmentHomeBinding::inflate

    override fun onStart() {
        super.onStart()
        fillUpperLinearLayoutWithCard()
        fillDownLinearLayoutWithCard()
    }

    private fun fillUpperLinearLayoutWithCard() {
        for (recipe in 0..20) {
            val itemCuisineBinding = ItemRecipesBinding.inflate(layoutInflater)
            binding.linearCardFast.addView(itemCuisineBinding.root)
        }
    }

    private fun fillDownLinearLayoutWithCard() {
        for (recipe in 0..20) {
            val itemCuisineBinding = ItemRecipesBinding.inflate(layoutInflater)
            binding.linearCardFast02.addView(itemCuisineBinding.root)
        }
    }


}