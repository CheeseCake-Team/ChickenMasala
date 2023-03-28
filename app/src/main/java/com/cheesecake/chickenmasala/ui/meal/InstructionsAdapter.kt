package com.cheesecake.chickenmasala.ui.meal

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.cheesecake.chickenmasala.R
import com.cheesecake.chickenmasala.databinding.ItemTranslatedInstructionBinding

class InstructionsAdapter :
    ListAdapter<String, InstructionsAdapter.InstructionsViewHolder>(InstructionDiffUtil()) {

    class InstructionsViewHolder(private val binding: ItemTranslatedInstructionBinding) :
        RecyclerView.ViewHolder(binding.root) {


        fun bind(item: String, position: Int) {
            binding.apply {
                textStep.text = textStep.context.getString(R.string.step_code, position + 1)
                textStepContent.text = item
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InstructionsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemTranslatedInstructionBinding.inflate(inflater, parent, false)
        return InstructionsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: InstructionsViewHolder, position: Int) {
        holder.bind(getItem(position), position)
    }
}

class InstructionDiffUtil : DiffUtil.ItemCallback<String>() {

    override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
        return oldItem === newItem
    }

    override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
        return oldItem == newItem
    }

}
