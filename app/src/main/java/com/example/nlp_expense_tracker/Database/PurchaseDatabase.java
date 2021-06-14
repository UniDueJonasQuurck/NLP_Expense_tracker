package com.example.nlp_expense_tracker.Database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import org.jetbrains.annotations.NotNull;


/// Creates an actual instance of the Database
@Database(entities = Database.class, version = 1)
public abstract class PurchaseDatabase extends RoomDatabase {

    private static PurchaseDatabase instance;

    public abstract DatabaseDao databaseDao();

    public static synchronized PurchaseDatabase getInstance(Context context) {
        if(instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    PurchaseDatabase.class, "note_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull @NotNull SupportSQLiteDatabase db) {
            super.onCreate(db);
        }
    };
}
