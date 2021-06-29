package com.example.nlp_expense_tracker.Database
import androidx.room.*
import kotlinx.coroutines.flow.Flow


@Dao
interface ReceiptDao {

        @Query("SELECT * FROM receipt_table ORDER BY date DESC")
        fun getAllReceipts(): Flow<List<Receipts>>


        @Query("SELECT SUM(total)AS sum_total FROM receipt_table")
        fun getSum(): Flow<String>

        @Query("SELECT total FROM receipt_table AS latest_total ORDER BY id DESC LIMIT 1")
        fun getLatestEntryTotal(): Flow<String>

        @Query("SELECT store FROM receipt_table AS latest_store ORDER BY id DESC LIMIT 1")
        fun getLatestEntryStore(): Flow<String>

        @Query("SELECT total FROM receipt_table  AS latest_total WHERE id = (SELECT MAX(id)-1  FROM receipt_table)")
        fun getSecondLatestEntryTotal(): Flow<String>

        @Query("SELECT store FROM receipt_table AS latest_store WHERE id = (SELECT MAX(id)-1  FROM receipt_table)")
        fun getSecondLatestEntryStore():Flow<String>

        @Query("SELECT total FROM receipt_table  AS latest_total WHERE id = (SELECT MAX(id)-2  FROM receipt_table)")
        fun getThirdLatestEntryTotal(): Flow<String>

        @Query("SELECT store FROM receipt_table AS latest_store WHERE id = (SELECT MAX(id)-2  FROM receipt_table)")
        fun getThirdLatestEntryStore():Flow<String>

        @Query("SELECT total FROM receipt_table  AS latest_total WHERE id = (SELECT MAX(id)-3  FROM receipt_table)")
        fun getFourthLatestEntryTotal(): Flow<String>

        @Query("SELECT store FROM receipt_table AS latest_store WHERE id = (SELECT MAX(id)-3  FROM receipt_table)")
        fun getFourthLatestEntryStore():Flow<String>


        @Insert(onConflict = OnConflictStrategy.REPLACE)
        suspend fun insert(receipts: Receipts)

        @Update
        suspend fun update(receipts: Receipts)

        @Delete
        suspend fun delete(receipts: Receipts)
    }
