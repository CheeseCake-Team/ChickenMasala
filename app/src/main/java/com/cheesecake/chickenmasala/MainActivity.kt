package com.cheesecake.chickenmasala

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.cheesecake.chickenmasala.dataSource.CsvDataSource
import com.cheesecake.chickenmasala.dataSource.CsvParser
import com.cheesecake.chickenmasala.databinding.ActivityMainBinding
import com.cheesecake.chickenmasala.model.Recipes
import com.cheesecake.chickenmasala.ui.*

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var recipes: Recipes
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecipes()
        loadFragmentIntoContainer(HomeFragment(recipes))
        binding.navBarButton.selectedItemId = R.id.home
    }

    override fun onStart() {
        super.onStart()
        addCallBacks()
    }

    private fun setupRecipes() {
        val parser = CsvParser()
        val dataSource = CsvDataSource(parser, assets.open(FILE_NAME))
        recipes = Recipes(dataSource.getAllMealsData())
    }


    private fun addCallBacks() {
        binding.navBarButton.setOnItemSelectedListener { item ->
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
                    loadFragmentIntoContainer(CuisineFragment())
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
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, baseFragment)
            .commit()
    }

    companion object {
        const val FILE_NAME = "indian_food_v3.csv"
    }
}