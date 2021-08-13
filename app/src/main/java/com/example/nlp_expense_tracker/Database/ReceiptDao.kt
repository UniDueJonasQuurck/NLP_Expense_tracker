package com.example.nlp_expense_tracker.Database
import androidx.room.*
import kotlinx.coroutines.flow.Flow


@Dao
interface ReceiptDao {

        @Query("SELECT * FROM receipt_table ORDER BY date DESC")
        fun getAllReceipts(): Flow<List<Receipts>>

        @Query("SELECT SUM(total)AS sum_total FROM receipt_table")
        fun getSum(): Flow<String>

        @Query("SELECT total FROM receipt_table AS latest_total1 ORDER BY id DESC LIMIT 1")
        fun getLatestEntryTotal(): Flow<String>

        @Query("SELECT store FROM receipt_table AS latest_store1 ORDER BY id DESC LIMIT 1")
        fun getLatestEntryStore(): Flow<String>

        @Query("SELECT total FROM receipt_table AS latest_total2 ORDER BY id DESC LIMIT 1 OFFSET 1")
        fun getSecondLatestEntryTotal(): Flow<String>

        @Query("SELECT store FROM receipt_table AS latest_store2 ORDER BY id DESC LIMIT 1 OFFSET 1")
        fun getSecondLatestEntryStore():Flow<String>

        @Query("SELECT total FROM receipt_table AS latest_total3 ORDER BY id DESC LIMIT 1 OFFSET 2")
        fun getThirdLatestEntryTotal(): Flow<String>

        @Query("SELECT store FROM receipt_table AS latest_store3 ORDER BY id DESC LIMIT 1 OFFSET 2")
        fun getThirdLatestEntryStore():Flow<String>

        @Query("SELECT total FROM receipt_table AS latest_total4 ORDER BY id DESC LIMIT 1 OFFSET 3")
        fun getFourthLatestEntryTotal(): Flow<String>

        @Query("SELECT store FROM receipt_table AS latest_store4 ORDER BY id DESC LIMIT 1 OFFSET 3")
        fun getFourthLatestEntryStore():Flow<String>


        @Insert(onConflict = OnConflictStrategy.REPLACE)
        suspend fun insert(receipts: Receipts)

        @Update
        suspend fun update(receipts: Receipts)

        @Delete
        suspend fun delete(receipts: Receipts)
    }
