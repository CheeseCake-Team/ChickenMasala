package com.cheesecake.chickenmasala.ui.history

import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import com.cheesecake.chickenmasala.R
import com.cheesecake.chickenmasala.databinding.FragmentHistoryBinding
import com.cheesecake.chickenmasala.ui.base.BaseFragment

class HistoryFragment : BaseFragment<FragmentHistoryBinding>() {
    override val bindingInflater: (LayoutInflater) -> FragmentHistoryBinding =
        FragmentHistoryBinding::inflate

    override fun hasBackButtonOrNot() {
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(false)
    }

    override fun setActionBarTitle() {
        (activity as AppCompatActivity).supportActionBar?.title =
            getString(R.string.history_of_indian_cuisine)

    }

}