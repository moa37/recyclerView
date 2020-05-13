package com.example.myapplication.Repository;

import android.os.AsyncTask;

import com.example.myapplication.db.AppDatabase;
import com.example.myapplication.model.contacts;

import java.util.List;

public class DeleteContactRepository extends AsyncTask<contacts,Void, List<contacts>> {
    AppDatabase db;
    private contactReopsitory.contactCallback callback;

    public DeleteContactRepository(AppDatabase db,contactReopsitory.contactCallback callback) {
        this.db = db;
        this.callback=callback;
    }

    @Override
    protected List<contacts> doInBackground(contacts... contacts) {
        for(contacts cont:contacts){
            db.appDao().deleteContact(cont);
        }
        return db.appDao().getallcontacts();
    }

    @Override
    protected void onPostExecute(List<contacts> contactsList) {
        super.onPostExecute(contactsList);
        callback.callBack(contactsList);
    }
}
