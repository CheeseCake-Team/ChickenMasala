package com.cheesecake.chickenmasala

import android.os.Bundle
import android.os.PersistableBundle
import android.view.LayoutInflater
import androidx.core.view.WindowCompat
import com.cheesecake.chickenmasala.databinding.ActivityMainBinding
import com.cheesecake.chickenmasala.datasource.CsvDataSource
import com.cheesecake.chickenmasala.datasource.CsvParser
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

    private lateinit var recipes: RecipesManager


    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        super.onCreate(savedInstanceState, persistentState)
        changeTopAppbarTitle(R.string.home)
        loadFragmentIntoContainer(HomeFragment(recipes))
        val toolbar = binding.toolbar
        setSupportActionBar(toolbar)
    }

    override fun onStart() {
        super.onStart()
        setupRecipes()
        binding.bottomNavigationMenu.selectedItemId = R.id.home
        addCallBacks()
    }

    private fun setupRecipes() {
        val parser = CsvParser()
        val dataSource = CsvDataSource(parser, assets.open(FILE_NAME))
        recipes = RecipesManager(dataSource.getAllMealsData())
    }


    private fun addCallBacks() {
        binding.bottomNavigationMenu.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.home -> {
                    changeTopAppbarTitle(R.string.home)
                    loadFragmentIntoContainer(HomeFragment(recipes))
                    true
                }
                R.id.search -> {
                    supportActionBar?.hide()
                    loadFragmentIntoContainer(SearchFragment.createFragment(recipes))
                    true
                }
                R.id.categories -> {
                    changeTopAppbarTitle(R.string.category)
                    loadFragmentIntoContainer(CategoriesFragment(recipes.getFastMeals()))
                    true
                }
                R.id.history -> {
                    changeTopAppbarTitle(R.string.history)
                    loadFragmentIntoContainer(HistoryFragment())
                    true
                }
                else -> false
            }

        }
    }


    private fun changeTopAppbarTitle(resourceString: Int) {
        if (supportActionBar?.isShowing == false) supportActionBar!!.show()
        supportActionBar!!.title = getString(resourceString)
    }

    private fun loadFragmentIntoContainer(baseFragment: BaseFragment<*>) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, baseFragment)
            .commit()
    }

    companion object {
        const val FILE_NAME = "indian_food_v3.csv"
    }
}