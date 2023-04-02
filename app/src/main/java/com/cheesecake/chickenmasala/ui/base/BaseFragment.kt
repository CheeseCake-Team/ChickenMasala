package com.cheesecake.chickenmasala.ui.base

import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.cheesecake.chickenmasala.R
import com.cheesecake.chickenmasala.datasource.Constants

abstract class BaseFragment<viewBinding : ViewBinding> : Fragment() {

    abstract val bindingInflater: (LayoutInflater) -> viewBinding
    private var _binding: viewBinding? = null

    protected val binding: viewBinding
        get() = _binding as viewBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = bindingInflater(layoutInflater)
        hasBackButtonOrNot()
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

}