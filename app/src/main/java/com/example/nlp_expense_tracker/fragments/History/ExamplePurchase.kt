package com.example.nlp_expense_tracker.fragments.History

import android.text.Editable
import android.widget.EditText
import androidx.room.PrimaryKey

data class ExamplePurchase(
    val date: String,
    val store: String,
    val amount: String,
    @PrimaryKey(autoGenerate = true) val id: Int= 0
)