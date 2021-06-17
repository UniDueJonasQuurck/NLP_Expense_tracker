package com.example.nlp_expense_tracker.Database

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize


@Entity(tableName = "receipt_table")
@Parcelize
data class Receipts(var total: String, var date: String , var store: String ,
    @PrimaryKey(autoGenerate = true) val id: Int= 0) : Parcelable
