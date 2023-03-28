package com.cheesecake.chickenmasala.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.cheesecake.chickenmasala.databinding.ItemAdviceBinding
import com.cheesecake.chickenmasala.model.Advice

class AdviceImageSliderAdapter :
    ListAdapter<Advice, AdviceImageSliderAdapter.AdviceViewHolder>(AdviceItemCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdviceViewHolder =
        AdviceViewHolder.from(parent)

    override fun onBindViewHolder(holder: AdviceViewHolder, position: Int) =
        holder.bind(getItem(position))

    class AdviceViewHolder private constructor(private var binding: ItemAdviceBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Advice) {
            binding.apply {
                adviceTitleText.text = item.title
                adviceBodyText.text = item.body
                adviceImage.setImageDrawable(
                    AppCompatResources.getDrawable(adviceImage.context, item.image)
                )
            }
        }

        companion object {
            fun from(parent: ViewGroup): AdviceViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemAdviceBinding.inflate(layoutInflater, parent, false)
                return AdviceViewHolder(binding)
            }
        }
    }
}

class AdviceItemCallback : DiffUtil.ItemCallback<Advice>() {
    override fun areItemsTheSame(oldItem: Advice, newItem: Advice): Boolean {
        return oldItem === newItem
    }

    override fun areContentsTheSame(oldItem: Advice, newItem: Advice): Boolean {
        return oldItem == newItem
    }
}