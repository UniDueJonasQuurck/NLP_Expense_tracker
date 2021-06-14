package com.example.nlp_expense_tracker.Database;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "note_table")
public class Database {

    @PrimaryKey(autoGenerate = true) //gibt jedem Eintrag in der Tabelle eine unique ID
    private int id;

    private String store; //room erstellt für jede variable eine Spalte in der Tabelle

    private String date;

    private String total;

    public Database(String store, String date, String total) {
        this.store = store;
        this.date = date;
        this.total = total;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getStore() {
        return store;
    }

    public String getDate() {
        return date;
    }

    public String getTotal() {
        return total;
    }
}
