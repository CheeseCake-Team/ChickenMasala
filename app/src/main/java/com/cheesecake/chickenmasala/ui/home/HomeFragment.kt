package com.cheesecake.chickenmasala.ui.home

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.cheesecake.chickenmasala.R
import com.cheesecake.chickenmasala.databinding.FragmentHomeBinding
import com.cheesecake.chickenmasala.model.Advice
import com.cheesecake.chickenmasala.model.Constants
import com.cheesecake.chickenmasala.model.Meal
import com.cheesecake.chickenmasala.model.RecipesManager
import com.cheesecake.chickenmasala.ui.base.BaseFragment
import com.cheesecake.chickenmasala.ui.meal.MealFragment
import com.cheesecake.chickenmasala.ui.search.SearchFragment
import java.util.Timer
import java.util.TimerTask

class HomeFragment : BaseFragment<FragmentHomeBinding>() {
    override val bindingInflater: (LayoutInflater) -> FragmentHomeBinding =
        FragmentHomeBinding::inflate

    private var recipesManager: RecipesManager? = null
    private lateinit var handler: Handler
    private lateinit var runnable: Runnable
    private var isUserInteracting = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recipesManager = arguments?.getParcelable(Constants.MAIN_ACTIVITY_RECIPES)
        val adviceAdapter = AdviceImageSliderAdapter()
        adviceAdapter.submitList(prepareFoodAdviceList(requireContext()))
        binding.adviceImageSlider.adapter = adviceAdapter

        initializeAutomaticAnimationOfImageSlider(adviceAdapter)
    }

    private fun initializeAutomaticAnimationOfImageSlider(adviceAdapter: AdviceImageSliderAdapter) {
        handler = Handler(Looper.getMainLooper())
        val delay = 7000L
        runnable = object : Runnable {
            override fun run() {
                if (!isUserInteracting) {
                    val currentItem = binding.adviceImageSlider.currentItem
                    val numItems = adviceAdapter.itemCount
                    binding.adviceImageSlider.setCurrentItem((currentItem + 1) % numItems, true)
                }
                handler.postDelayed(this, delay)
            }
        }
        handler.postDelayed(runnable, delay)


        binding.adviceImageSlider.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageScrollStateChanged(state: Int) {
                isUserInteracting = state != ViewPager2.SCROLL_STATE_IDLE
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        handler.removeCallbacks(runnable)
    }

    private fun prepareFoodAdviceList(context: Context): List<Advice> {
        val adviceList = mutableListOf<Advice>()

        adviceList.add(Advice(context.getString(R.string.eat_fruits_veggies_title),
            context.getString(R.string.eat_fruits_veggies_body), R.drawable.fruits_image))
        adviceList.add(Advice(context.getString(R.string.limit_processed_foods_title),
            context.getString(R.string.limit_processed_foods_body), R.drawable.food_image_six))
        adviceList.add(Advice(context.getString(R.string.choose_lean_protein_title),
            context.getString(R.string.choose_lean_protein_body), R.drawable.food_image_thirteen))
        adviceList.add(Advice(context.getString(R.string.reduce_added_sugar_title),
            context.getString(R.string.reduce_added_sugar_body), R.drawable.suger_image))
        adviceList.add(Advice(context.getString(R.string.stay_hydrated_title),
            context.getString(R.string.stay_hydrated_body), R.drawable.water_image))
        adviceList.add(Advice(context.getString(R.string.choose_healthy_fats_title),
            context.getString(R.string.choose_healthy_fats_body), R.drawable.food_image_ten))
        adviceList.add(Advice(context.getString(R.string.eat_breakfast_title),
            context.getString(R.string.eat_breakfast_body), R.drawable.food_image_nine))

        return adviceList
    }

    override fun onStart() {
        super.onStart()
        val fastRecipeAdapter =
            HomeRecipeAdapter(HomeRecipeListener { loadMealFragment(it) }).apply {
                submitList(recipesManager?.getFastMeals())
            }
        val recipesOfTodayAdapter =
            HomeRecipeAdapter(HomeRecipeListener { loadMealFragment(it) }).apply {
                submitList(recipesManager?.getRandomMeals())
            }
        val lowIngredientsFoodAdapter =
            HomeRecipeAdapter(HomeRecipeListener { loadMealFragment(it) }).apply {
                submitList(recipesManager?.getLessIngredientMeals())
            }
        binding.apply {
            recyclerViewFastRecipes.adapter = fastRecipeAdapter
            recyclerViewRecipesOfToday.adapter = recipesOfTodayAdapter
            recyclerViewLowIngredientsFood.adapter = lowIngredientsFoodAdapter
        }
    }

    private fun loadMealFragment(meal: Meal) {
        val transaction = requireActivity().supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container, MealFragment.createFragment(meal))
        transaction.addToBackStack(null)
        transaction.commit()
    }

    companion object {
        fun createFragment(recipesManager: RecipesManager) = HomeFragment().apply {
            arguments = Bundle().apply {
                putParcelable(Constants.MAIN_ACTIVITY_RECIPES, recipesManager)
            }
        }
    }
}