package com.example.nlp_expense_tracker.Database

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class) //database is created for the whole app, not only one activity
object AppModule {

    @Provides
    @Singleton //only one instance of database is created
    fun provideDatabase( //Code that constructs database
        app: Application,
        callback: ReceiptDatabase.Callback
    ) = Room.databaseBuilder(app, ReceiptDatabase::class.java, "receipt_database")
        .fallbackToDestructiveMigration()
        .addCallback(callback)
        .allowMainThreadQueries()
        .build()

    @Provides //Creates TaskDao to access database
    fun provideTaskDao(db: ReceiptDatabase) = db.receiptDao()

    @ApplicationScope
    @Provides
    @Singleton
    fun provideApplicationScope() = CoroutineScope(SupervisorJob())
}

@Retention(AnnotationRetention.RUNTIME) ///Makes sure that there is only one Courotine Scope in the whole Application
@Qualifier
annotation class ApplicationScope