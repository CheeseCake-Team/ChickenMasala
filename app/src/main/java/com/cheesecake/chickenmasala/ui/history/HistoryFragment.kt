package com.cheesecake.chickenmasala.ui.history

import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import com.cheesecake.chickenmasala.databinding.FragmentHistoryBinding
import com.cheesecake.chickenmasala.ui.base.BaseFragment

class HistoryFragment : BaseFragment<FragmentHistoryBinding>() {
    override val bindingInflater: (LayoutInflater) -> FragmentHistoryBinding =
        FragmentHistoryBinding::inflate

    override fun hasBackButtonOrNot() {
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(false)
    }


}