package com.cheesecake.chickenmasala.ui.meal

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.cheesecake.chickenmasala.databinding.ItemTranslatedInstructionBinding

class InstructionAdapter() :
    ListAdapter<String, InstructionAdapter.IngredientViewHolder>(IngredientItemCallback) {

    companion object IngredientItemCallback : DiffUtil.ItemCallback<String>() {
        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }
    }


    class IngredientViewHolder(private var binding: ItemTranslatedInstructionBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: String, position: Int) {
            val numberOfInstruction ="Step ${ position + 1 }"
            binding.apply {
                instructionNumber.text = numberOfInstruction
                instruction.text = item
            }
        }

        companion object {
            fun from(parent: ViewGroup): IngredientViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding =
                    ItemTranslatedInstructionBinding.inflate(layoutInflater, parent, false)
                return IngredientViewHolder(binding)
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IngredientViewHolder =
        IngredientViewHolder.from(parent)

    override fun onBindViewHolder(holder: IngredientViewHolder, position: Int) =
        holder.bind(getItem(position), position)
}

