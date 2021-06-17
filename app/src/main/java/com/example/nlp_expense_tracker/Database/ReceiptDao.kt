package com.example.nlp_expense_tracker.Database
import androidx.room.*
import kotlinx.coroutines.flow.Flow


@Dao
interface ReceiptDao {

        @Query("SELECT * FROM receipt_table")
        fun getAllReceipts(): Flow<List<Receipts>>

        @Insert(onConflict = OnConflictStrategy.REPLACE)
        suspend fun insert(receipts: Receipts)

        @Update
        suspend fun update(receipts: Receipts)

        @Delete
        suspend fun delete(receipts: Receipts)
    }
