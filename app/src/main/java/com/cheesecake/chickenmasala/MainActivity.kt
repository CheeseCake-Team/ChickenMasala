package com.cheesecake.chickenmasala

import android.os.Bundle
import android.os.PersistableBundle
import android.view.LayoutInflater
import com.cheesecake.chickenmasala.dataSource.CsvDataSource
import com.cheesecake.chickenmasala.dataSource.CsvParser
import com.cheesecake.chickenmasala.databinding.ActivityMainBinding
import com.cheesecake.chickenmasala.model.Recipes
import com.cheesecake.chickenmasala.ui.base.BaseActivity
import com.cheesecake.chickenmasala.ui.base.BaseFragment
import com.cheesecake.chickenmasala.ui.cuisine.CuisineFragment
import com.cheesecake.chickenmasala.ui.history.HistoryFragment
import com.cheesecake.chickenmasala.ui.home.HomeFragment
import com.cheesecake.chickenmasala.ui.search.SearchFragment

class MainActivity : BaseActivity<ActivityMainBinding>() {

    override val bindingInflater: (LayoutInflater) -> ActivityMainBinding
        get() = ActivityMainBinding::inflate

    private lateinit var recipes: Recipes

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setupRecipes()
        loadFragmentIntoContainer(HomeFragment(recipes))
    }

    override fun onStart() {
        super.onStart()
        binding.navBarButton.selectedItemId = R.id.home
        addCallBacks()
    }

    private fun setupRecipes() {
        val parser = CsvParser()
        val dataSource = CsvDataSource(parser, assets.open(FILE_NAME))
        recipes = Recipes(dataSource.getAllMealsData())
    }


    private fun addCallBacks() {
        binding!!.navBarButton.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.home -> {
                    loadFragmentIntoContainer(HomeFragment(recipes))
                    true
                }
                R.id.search -> {
                    loadFragmentIntoContainer(SearchFragment())
                    true
                }
                R.id.cuisine -> {
                    loadFragmentIntoContainer(CuisineFragment(recipes))
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
        supportActionBar?.title =
            getString(resourceString)
    }

    private fun loadFragmentIntoContainer(baseFragment: BaseFragment<*>){
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, baseFragment)
            .commit()
    }

    companion object {
        const val FILE_NAME = "indian_food_v3.csv"
    }
}