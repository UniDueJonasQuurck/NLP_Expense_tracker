package com.example.nlp_expense_tracker.fragments.Graph


import com.example.nlp_expense_tracker.Database.Receipts
import com.robinhood.spark.SparkAdapter


class ReceiptsSparkAdapter (private val receipt: List<Receipts>): SparkAdapter() {
    override fun getCount() = receipt.size

    override fun getItem(index: Int) = receipt[index]

    override fun getY(index: Int): Float {
        val currentReceiptTotal = receipt[index]
        return currentReceiptTotal.total.toFloat()
    }

    override fun getX (index: Int): Float {
        val currentReceiptDate = receipt[index]
        return currentReceiptDate.date.toFloat()
    }
}