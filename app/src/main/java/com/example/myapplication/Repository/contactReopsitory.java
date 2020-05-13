package com.example.myapplication.Repository;

import android.os.AsyncTask;

import com.example.myapplication.db.AppDatabase;
import com.example.myapplication.model.contacts;

import java.util.List;

public class contactReopsitory extends AsyncTask<Void,Void, List<contacts>> {
    AppDatabase db;
   private contactCallback callback;

    public contactReopsitory(AppDatabase db ,contactCallback callback) {
        this.db = db;
        this.callback=callback;
    }

  public interface contactCallback{
        void callBack(List<contacts> contactsList);
    }

    @Override
    protected List<contacts> doInBackground(Void... voids) {
        return db.appDao().getallcontacts();
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(List<contacts> contacts) {
        super.onPostExecute(contacts);
        callback.callBack(contacts);
    }
}
