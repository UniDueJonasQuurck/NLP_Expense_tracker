package com.example.nlp_expense_tracker.Database

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "receipt_table")

data class Receipts(var total: String, var date: String , var store: String ,
    @PrimaryKey(autoGenerate = true) val id: Int= 0)
