package com.example.nlp_expense_tracker.fragments.History

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.nlp_expense_tracker.R

class HeaderAdapter: RecyclerView.Adapter<HeaderAdapter.HeaderViewHolder>() {


    /* Inflates view and returns HeaderViewHolder. */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeaderViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.history_header, parent, false)
        return HeaderViewHolder(view)
    }


    /* Returns number of items, since there is only one item in the header return one  */
    override fun getItemCount(): Int {
        return 1
    }

    override fun onBindViewHolder(holder: HeaderViewHolder, position: Int) {
        holder.headerDateTextView
        holder.headerStoreTextView
        holder.headerTotalTextView
    }

    /* ViewHolder for displaying header. */
    class HeaderViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val headerStoreTextView: TextView = itemView.findViewById(R.id.headerStore)
        val headerDateTextView: TextView = itemView.findViewById(R.id.headerDate)
        val headerTotalTextView: TextView = itemView.findViewById(R.id.headerToal)


    }

}