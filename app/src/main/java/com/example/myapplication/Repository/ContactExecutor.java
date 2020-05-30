package com.example.myapplication.Repository;

import com.example.myapplication.db.AppDao;
import com.example.myapplication.model.contacts;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class ContactExecutor {
    private AppDao dao;
    private Executor executor = Executors.newSingleThreadExecutor();
    private contactRepository.contactCallback callback;

    public ContactExecutor(AppDao dao, contactRepository.contactCallback callback) {
        this.dao = dao;
        this.callback = callback;
    }

    public void addContact(final contacts contacts){
        executor.execute(new Runnable() {
            @Override
            public void run() {
                dao.insertall(contacts);
                postContact();
            }
        });
    }

    public void deleteContact(final contacts contacts){
        executor.execute(new Runnable() {
            @Override
            public void run() {
                dao.deleteContact(contacts);
                postContact();
            }
        });
    }
    public void updateContact(final contacts contacts){
        executor.execute(new Runnable() {
            @Override
            public void run() {
                dao.updateContact(contacts);
                postContact();
            }
        });
    }

    private void postContact(){
        List<contacts> mcontacts = dao.getallcontacts();
        callback.callBack(mcontacts);
    }
}