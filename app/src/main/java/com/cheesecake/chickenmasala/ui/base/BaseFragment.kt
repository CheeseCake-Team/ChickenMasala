package com.cheesecake.chickenmasala.ui.base

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.cheesecake.chickenmasala.R
import com.cheesecake.chickenmasala.model.Constants

abstract class BaseFragment<viewBinding : ViewBinding> : Fragment() {

    abstract val bindingInflater: (LayoutInflater) -> viewBinding
    private var _binding: viewBinding? = null
    lateinit var sharedPreferences: SharedPreferences
    lateinit var data : Any

    protected val binding: viewBinding
        get() = _binding as viewBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = bindingInflater(layoutInflater)
        sharedPreferences =
            requireActivity().getSharedPreferences(
                Constants.Keys.SHARED_FRAGMENT,
                Context.MODE_PRIVATE)

        hasBackButtonOrNot()
        setActionBarTitle()
        return _binding!!.root
    }



    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    fun loadFragment(fragment: Fragment) {
        requireActivity().supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragment_container,fragment)
            addToBackStack(null)
            commit()
        }
    }

    fun <T : Parcelable> createArgument(value: T, fragment: Fragment) =
        fragment.apply {
            arguments = Bundle().apply {
                putParcelable(Constants.Keys.ARGUMENT, value)
            }
        }

    abstract fun hasBackButtonOrNot()

    abstract fun setActionBarTitle()

    fun saveStatue(view: View, key: String) {
        when (view) {
            is RecyclerView -> {
                val layoutManager = view.layoutManager as LinearLayoutManager
                data = layoutManager.findFirstVisibleItemPosition()
                sharedPreferences
                    .edit().putInt(
                        key, data as Int
                    )
                    .apply()
            }
            is ViewGroup -> {
                data = view.scrollY
                sharedPreferences
                    .edit().putInt(
                        key, data as Int
                    )
                    .apply()
            }
        }

    }

    fun returnStatue(view: View, key: String) {
        when (view) {
            is RecyclerView -> {
                data =  sharedPreferences.getInt(key, 0)
                view.layoutManager?.scrollToPosition(data as Int)
            }
            is ViewGroup -> {
                data =  sharedPreferences.getInt(key, 0)
                view.post { view.scrollTo(0, data as Int) }

            }
        }



    }
}