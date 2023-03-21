package com.cheesecake.chickenmasala.ui

import android.view.LayoutInflater
import com.cheesecake.chickenmasala.databinding.FragmentHistoryBinding

class HistoryFragment : BaseFragment<FragmentHistoryBinding>() {
    override val bindingInflater: (LayoutInflater) -> FragmentHistoryBinding =
        FragmentHistoryBinding::inflate


}