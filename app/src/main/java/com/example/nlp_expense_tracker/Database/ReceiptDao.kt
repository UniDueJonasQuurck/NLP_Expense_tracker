package com.example.nlp_expense_tracker.Database
import androidx.room.*
import kotlinx.coroutines.flow.Flow


@Dao
interface ReceiptDao {

        @Query("SELECT * FROM receipt_table ORDER BY date DESC")
        fun getAllReceipts(): Flow<List<Receipts>>


        @Query("SELECT COALESCE(sum(COALESCE(total,0)), 0) From receipt_table")
        fun getSum(): Flow<List<Double>>

        @Insert(onConflict = OnConflictStrategy.REPLACE)
        suspend fun insert(receipts: Receipts)

        @Update
        suspend fun update(receipts: Receipts)

        @Delete
        suspend fun delete(receipts: Receipts)
    }
