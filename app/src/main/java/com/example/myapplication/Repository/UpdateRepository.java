package com.example.myapplication.Repository;


import android.os.AsyncTask;

import com.example.myapplication.db.AppDatabase;
import com.example.myapplication.model.contacts;

import java.util.List;

public class UpdateRepository extends AsyncTask<contacts,Void, List<contacts>> {
    AppDatabase db;
    UpdateCallback callback;


    public UpdateRepository(AppDatabase db, UpdateCallback callback) {
        this.db = db;
        this.callback = callback;
    }

    public interface UpdateCallback{
        void updateCallBack(List<contacts> contactsList);
    }

    @Override
    protected List<contacts> doInBackground(contacts... contacts) {
            db.appDao().updateContact(contacts);
        return db.appDao().getallcontacts();
    }

    @Override
    protected void onPostExecute(final List<contacts> contactsList) {
        super.onPostExecute(contactsList);
        callback.updateCallBack(contactsList);



    }
}
