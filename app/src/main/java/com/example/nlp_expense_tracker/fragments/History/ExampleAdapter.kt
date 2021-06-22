package com.example.nlp_expense_tracker.fragments.History

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.nlp_expense_tracker.Database.Receipts
import com.example.nlp_expense_tracker.databinding.ReceiptsBinding


class ExampleAdapter : ListAdapter<Receipts,ExampleAdapter.ExampleViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExampleViewHolder { // Basically how to get a new Item from the List and display it
        val binding = ReceiptsBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ExampleViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ExampleViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.bind(currentItem)
    }

    class ExampleViewHolder(private val binding: ReceiptsBinding) : RecyclerView.ViewHolder(binding.root){ //Examples One Row in our list
        fun bind (receipts: Receipts) {
            binding.apply {
                storeHistory.text = receipts.store
                amountHistory.text = receipts.total
                dateHistory.text  = receipts.date
            }
        }
    }


    class DiffCallback : DiffUtil.ItemCallback<Receipts>() {
        override fun areItemsTheSame(oldItem: Receipts, newItem: Receipts) =
            oldItem.id == newItem.id


        override fun areContentsTheSame(oldItem: Receipts, newItem: Receipts) =
            oldItem == newItem
                    && oldItem.total == newItem.total
                    && oldItem.date == newItem.date
                    && oldItem.store == newItem.store
    }

}