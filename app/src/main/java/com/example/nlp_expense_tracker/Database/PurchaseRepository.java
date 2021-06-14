package com.example.nlp_expense_tracker.Database;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class PurchaseRepository {
    private DatabaseDao databaseDao;
    private LiveData<List<Database>> allData;

    public PurchaseRepository(Application application){
        PurchaseDatabase database = PurchaseDatabase.getInstance(application);
        databaseDao = database.databaseDao();
        allData = databaseDao.getAllNotes();
    }
    public void insert (Database database){
        new InsertDataAsyncTask(databaseDao).execute(database);
    }
    public void update (Database database){
        new UpdateDataAsyncTask(databaseDao).execute(database);
    }
    public void delete (Database database){
        new DeleteDataAsyncTask(databaseDao).execute(database);
    }
    public void deleteAllNotes (){
        new DeleteAllNotesAsyncTask(databaseDao).execute();
    }

    public LiveData<List<Database>> getAllData() {
        return allData;
    }

    private static class InsertDataAsyncTask extends AsyncTask<Database,Void,Void>{
        private DatabaseDao databaseDao;
        private InsertDataAsyncTask(DatabaseDao databaseDao){
            this.databaseDao = databaseDao;
        }
        @Override
        protected Void doInBackground(Database... databases) {
            databaseDao.insert(databases[0]);
            return null;
        }
    }
    private static class DeleteAllNotesAsyncTask extends AsyncTask<Void,Void,Void>{
        private DatabaseDao databaseDao;
        private DeleteAllNotesAsyncTask(DatabaseDao databaseDao){
            this.databaseDao = databaseDao;
        }
        @Override
        protected Void doInBackground(Void... voids) {
            databaseDao.deleteAllNotes();
            return null;
        }
    }
    private static class UpdateDataAsyncTask extends AsyncTask<Database,Void,Void>{
        private DatabaseDao databaseDao;
        private UpdateDataAsyncTask(DatabaseDao databaseDao){
            this.databaseDao = databaseDao;
        }
        @Override
        protected Void doInBackground(Database... databases) {
            databaseDao.update(databases[0]);
            return null;
        }
    }
    private static class DeleteDataAsyncTask extends AsyncTask<Database,Void,Void>{
        private DatabaseDao databaseDao;
        private DeleteDataAsyncTask(DatabaseDao databaseDao){
            this.databaseDao = databaseDao;
        }
        @Override
        protected Void doInBackground(Database... databases) {
            databaseDao.delete(databases[0]);
            return null;
        }
    }
}
