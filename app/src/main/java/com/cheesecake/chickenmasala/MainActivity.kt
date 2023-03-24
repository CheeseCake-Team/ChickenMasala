package com.cheesecake.chickenmasala

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.cheesecake.chickenmasala.databinding.ActivityMainBinding
import com.cheesecake.chickenmasala.databinding.FragmentHomeBinding
import com.cheesecake.chickenmasala.ui.*

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        loadFragmentIntoContainer(HomeFragment())
        binding.navBarButton.selectedItemId = R.id.home
    }

    override fun onStart() {
        super.onStart()
        addCallBacks()
    }




    private fun addCallBacks(){
        binding.navBarButton.setOnItemSelectedListener {item ->
            when(item.itemId) {
                R.id.home -> {
                    loadFragmentIntoContainer(HomeFragment())
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
            .replace(R.id.fragment_container,baseFragment)
            .commit()
    }

}