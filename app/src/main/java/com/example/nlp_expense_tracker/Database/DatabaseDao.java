package com.example.nlp_expense_tracker.Database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface DatabaseDao { //Data Acces Object

    @Insert
    void insert(Database database);

    @Update
    void update(Database database);

    @Delete
    void delete(Database database);

    @Query("DELETE FROM note_table")
    void deleteAllNotes();

    @Query("SELECT * FROM note_table ORDER BY date DESC") //Returns all our entries
    LiveData<List<Database>> getAllNotes();

}
