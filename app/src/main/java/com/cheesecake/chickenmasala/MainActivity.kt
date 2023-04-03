package com.cheesecake.chickenmasala

import android.view.LayoutInflater
import android.view.MenuItem
import android.widget.Toast
import com.cheesecake.chickenmasala.databinding.ActivityMainBinding
import com.cheesecake.chickenmasala.datasource.CsvDataSource
import com.cheesecake.chickenmasala.datasource.CsvParser
import com.cheesecake.chickenmasala.interactor.RecipesInteractor
import com.cheesecake.chickenmasala.ui.base.BaseActivity
import com.cheesecake.chickenmasala.ui.base.BaseFragment
import com.cheesecake.chickenmasala.ui.categories.CategoriesFragment
import com.cheesecake.chickenmasala.ui.cuisines.CuisinesFragment
import com.cheesecake.chickenmasala.ui.history.HistoryFragment
import com.cheesecake.chickenmasala.ui.home.HomeFragment
import com.cheesecake.chickenmasala.ui.search.SearchFragment


class MainActivity : BaseActivity<ActivityMainBinding>() {

    override val bindingInflater: (LayoutInflater) -> ActivityMainBinding
        get() = ActivityMainBinding::inflate

    override fun onStart() {
        super.onStart()
        this.supportActionBar?.setDisplayHomeAsUpEnabled(true)
        setupRecipes()
        initializeHomeScreen()
        addCallBacks()
    }

    private fun setupRecipes() {
        val indianMeals = CsvDataSource(CsvParser(), assets.open("indian_food_v3.csv"))
            .getAllMealsData()
        RecipesInteractor.initialize(indianMeals)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            super.onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }


    private fun initializeHomeScreen() {
        binding.bottomNavigationMenu.selectedItemId = R.id.home
        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(binding.fragmentContainer.id, HomeFragment()).commit()
    }


    private fun addCallBacks() {
        binding.bottomNavigationMenu.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.home -> {
                    loadFragmentIntoContainer(HomeFragment())
                    true
                }
                R.id.search -> {
                    loadFragmentIntoContainer(SearchFragment())
                    true
                }
                R.id.categories -> {
                    loadFragmentIntoContainer(CategoriesFragment())
                    true
                }
                R.id.cuisine -> {
                    loadFragmentIntoContainer(CuisinesFragment())
                    true
                }
                R.id.history -> {
                    loadFragmentIntoContainer(HistoryFragment())
                    true
                }
                else -> false
            }

        }
    }

    private fun loadFragmentIntoContainer(baseFragment: BaseFragment<*>) {
        supportFragmentManager.beginTransaction().replace(R.id.fragment_container, baseFragment)
            .commit()
    }

}