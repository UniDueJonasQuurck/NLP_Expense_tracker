package com.example.nlp_expense_tracker.Database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Provider

@Database(entities = [Receipts::class],version = 1, exportSchema = false)
abstract class ReceiptDatabase :RoomDatabase(){

    abstract fun receiptDao(): ReceiptDao

    //Fills Database with dummy data
        class Callback @Inject constructor( //tells Dagger how to create an intance of the class and its dependecies
            private val database: Provider<ReceiptDatabase>, //Task database is only instantiated when we call onCreate which only is called AFTER Database is craeted.
            @ApplicationScope private val applicationScope: CoroutineScope
        ) : RoomDatabase.Callback() {

            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)

                val dao = database.get().receiptDao()
                applicationScope.launch {
                    dao.insert(Receipts("14,76€","15.06.2021","Aldi"))
                    dao.insert(Receipts("8,67€","13.06.2021","Lidl"))
                    dao.insert(Receipts("10,99€","11.06.2021","Aldi"))
                    dao.insert(Receipts("1,99€","11.06.2021","Aldi"))
                    dao.insert(Receipts("4,70€","11.06.2021","Rewe"))
                    dao.insert(Receipts("67,49€","10.06.2021","Aldi"))
                    dao.insert(Receipts("76,90€","07.06.2021","Ikea"))
                    dao.insert(Receipts("24,67€","01.06.2021","Edeka"))
                }
            }
        }
    }