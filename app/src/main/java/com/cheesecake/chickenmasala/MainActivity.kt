package com.cheesecake.chickenmasala

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import com.cheesecake.chickenmasala.databinding.ActivityMainBinding
import com.cheesecake.chickenmasala.model.Constants
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
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedPreferences = getSharedPreferences(Constants.Keys.SHARED_ACTIVITY, Context.MODE_PRIVATE)


    }

    override fun onResume() {
        super.onResume()
        restoreFragmentState()
    }

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
                    changeAppBarTitle(R.string.home)
                    loadFragmentIntoContainer(HomeFragment())
                    true
                }
                R.id.search -> {
                    changeAppBarTitle(R.string.search)
                    loadFragmentIntoContainer(SearchFragment())
                    true
                }
                R.id.categories -> {
                    changeAppBarTitle(R.string.category)
                    loadFragmentIntoContainer(CategoriesFragment())
                    true
                }
                R.id.cuisine -> {
                    changeAppBarTitle(R.string.cuisine)
                    loadFragmentIntoContainer(CuisinesFragment())
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

    private fun restoreFragmentState() {


        supportActionBar?.title = sharedPreferences.getString(
            Constants.Keys.SAVE_TITLE_STATE, "")

        binding.bottomNavigationMenu.selectedItemId =
            sharedPreferences.getInt(Constants.Keys.SAVE_BOTTOM_NAV_STATE, 0)



    }

    private fun saveFragmentState() {
        val editor = sharedPreferences.edit()

        editor.putString(
            Constants.Keys.SAVE_TITLE_STATE,
            supportActionBar?.title.toString())

        editor.putInt(
            Constants.Keys.SAVE_BOTTOM_NAV_STATE,
            binding.bottomNavigationMenu.selectedItemId)

        editor.apply()
    }

    override fun onStop() {
        super.onStop()
        saveFragmentState()
    }


}