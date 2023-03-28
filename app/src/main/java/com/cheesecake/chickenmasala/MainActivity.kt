package com.cheesecake.chickenmasala

import android.os.Bundle
import android.os.PersistableBundle
import android.view.LayoutInflater
import com.cheesecake.chickenmasala.databinding.ActivityMainBinding
import com.cheesecake.chickenmasala.datasource.CsvDataSource
import com.cheesecake.chickenmasala.datasource.CsvParser
import com.cheesecake.chickenmasala.model.Meal
import com.cheesecake.chickenmasala.model.RecipesManager
import com.cheesecake.chickenmasala.ui.base.BaseActivity
import com.cheesecake.chickenmasala.ui.base.BaseFragment
import com.cheesecake.chickenmasala.ui.categories.CategoriesFragment
import com.cheesecake.chickenmasala.ui.history.HistoryFragment
import com.cheesecake.chickenmasala.ui.home.HomeFragment
import com.cheesecake.chickenmasala.ui.search.SearchFragment

class MainActivity : BaseActivity<ActivityMainBinding>() {

    override val bindingInflater: (LayoutInflater) -> ActivityMainBinding
        get() = ActivityMainBinding::inflate

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)

    }

    override fun onStart() {
        super.onStart()
        starts(setupRecipes())
        binding.bottomNavigationMenu.selectedItemId = R.id.home
        addCallBacks()
        initializeHomeScreenAtStart()
    }

    private fun starts(setupRecipes: List<Meal>) {
        RecipesManager.initialize(setupRecipes)
    }

    private fun setupRecipes(): List<Meal> {
        return CsvDataSource(CsvParser(), assets.open(FILE_NAME)).getAllMealsData()
    }


    private fun initializeHomeScreenAtStart(){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(binding.fragmentContainer.id, HomeFragment()).commit()
    }


    private fun addCallBacks() {
        binding.bottomNavigationMenu.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.home -> {
                    changeAppBarTitle(R.string.home)
                    loadFragmentIntoContainer(HomeFragment.createFragment(
                        RecipesManager))
                    true
                }
                R.id.search -> {
                    changeAppBarTitle(R.string.search)
                    loadFragmentIntoContainer(
                        SearchFragment.newInstance(RecipesManager.indianFoodSearch))
                    true
                }
                R.id.categories -> {
                    changeAppBarTitle(R.string.category)
                    loadFragmentIntoContainer(CategoriesFragment())
                    true
                }
                R.id.history -> {
                    changeAppBarTitle(R.string.history_of_indian_cuisine)
                    loadFragmentIntoContainer(HistoryFragment())
                    true
                }
                else -> false
            }

        }
    }


    private fun changeAppBarTitle(resourceString: Int) {
        if (supportActionBar?.isShowing != true) supportActionBar?.show()
        supportActionBar?.title = getString(resourceString)
    }

    private fun loadFragmentIntoContainer(baseFragment: BaseFragment<*>) {
        supportFragmentManager.beginTransaction().replace(R.id.fragment_container, baseFragment)
            .commit()
    }

    companion object {
        const val FILE_NAME = "indian_food_v3.csv"
    }
}