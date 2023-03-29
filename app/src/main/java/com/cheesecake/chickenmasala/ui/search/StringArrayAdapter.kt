package com.cheesecake.chickenmasala.ui.search

import android.content.Context
import android.widget.ArrayAdapter
import android.widget.Filter

class StringArrayAdapter(
    private val allSuggestions: List<String>,
    context: Context,
    private val searchBarInputs: MutableList<String>
) : ArrayAdapter<String>(
    context,
    android.R.layout.simple_dropdown_item_1line,
    allSuggestions.toMutableList()
) {
    override fun getFilter(): Filter = object : Filter() {
        override fun performFiltering(constraint: CharSequence?): FilterResults =
            FilterResults().apply {
                constraint?.let { query ->
                    val filteredSuggestions = allSuggestions.filter {
                        it.contains(query, ignoreCase = true) && it !in searchBarInputs
                    }
                    values = filteredSuggestions
                    count = filteredSuggestions.size
                }
            }

        override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
            if ((results?.count ?: 0) > 0) {
                clear()
                if (results?.values is Collection<*>) {
                    @Suppress("UNCHECKED_CAST")
                    addAll(results.values as Collection<String>)
                    notifyDataSetChanged()
                }
            }
        }
    }
}