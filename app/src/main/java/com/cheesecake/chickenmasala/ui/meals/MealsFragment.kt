package com.cheesecake.chickenmasala.ui.meals

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import com.cheesecake.chickenmasala.databinding.FragmentMealsBinding
import com.cheesecake.chickenmasala.model.Meal
import com.cheesecake.chickenmasala.ui.base.BaseFragment
class MealsFragment(private val meals: List<Meal>) :
    BaseFragment<FragmentMealsBinding>() {


    override val bindingInflater: (LayoutInflater) -> FragmentMealsBinding =
        FragmentMealsBinding::inflate


    private lateinit var mealsAdapter: MealsAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        installViews()
    }


    private fun installViews() {
        //mealsAdapter = MealsAdapter()
        binding.recyclerMeals.adapter = mealsAdapter
    }

}



