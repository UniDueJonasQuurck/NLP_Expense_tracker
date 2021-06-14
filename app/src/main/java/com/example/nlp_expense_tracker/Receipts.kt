package com.example.nlp_expense_tracker

import androidx.room.PrimaryKey

data class Receipts(var total: String = "", var date: String = "", var store: String = "",
    @PrimaryKey(autoGenerate = true) val id: Int= 0)
