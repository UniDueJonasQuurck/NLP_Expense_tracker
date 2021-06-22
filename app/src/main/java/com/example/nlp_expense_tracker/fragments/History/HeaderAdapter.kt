package com.example.nlp_expense_tracker.fragments.History

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.nlp_expense_tracker.Database.ReceiptDao
import com.example.nlp_expense_tracker.R
import javax.inject.Inject

class HeaderAdapter: RecyclerView.Adapter<HeaderAdapter.HeaderViewHolder>() {
    private var totalSum: List<Double> = listOf(0.0)

    /* ViewHolder for displaying header. */
    class HeaderViewHolder(view: View) : RecyclerView.ViewHolder(view){
        private val headerTotal: TextView = itemView.findViewById(R.id.headerTotal)

        fun bind(totalSum: Int) {
            headerTotal.text = totalSum.toString()
        }
    }

    /* Inflates view and returns HeaderViewHolder. */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeaderViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.history_header, parent, false)
        return HeaderViewHolder(view)
    }

    /* Binds number of flowers to the header. */
    override fun onBindViewHolder(holder: HeaderViewHolder, position: Int) {
        holder.bind(position)
    }

    /* Returns number of items, since there is only one item in the header return one  */
    override fun getItemCount(): Int {
        return 1
    }

    /* Updates header to display number of flowers when a flower is added or subtracted. */
    fun updateTotalSum(updatedTotalSum: List<Double>) {
        totalSum = updatedTotalSum
        notifyDataSetChanged()
    }
}