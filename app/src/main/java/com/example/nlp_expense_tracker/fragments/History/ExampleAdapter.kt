package com.example.nlp_expense_tracker.fragments.History

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.nlp_expense_tracker.R
import com.example.nlp_expense_tracker.databinding.FragmentScanBinding

class ExampleAdapter (private val exampleList: List<ExamplePurchase>):RecyclerView.Adapter<ExampleAdapter.ExampleViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExampleViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.example_purchase,
            parent,false)

        return ExampleViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ExampleViewHolder, position: Int) {
        val currentItem = exampleList[position]

        holder.store_history.text =currentItem.store
        holder.amount_history.text = currentItem.amount
        holder.date_history.text = currentItem.date
    }

    override fun getItemCount() = exampleList.size

    class ExampleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){ //Examples One Row in our list
        val store_history: TextView = itemView.findViewById(R.id.store_history)
        val amount_history: TextView = itemView.findViewById(R.id.amount_history)
        val date_history: TextView = itemView.findViewById(R.id.date_history)
    }

}