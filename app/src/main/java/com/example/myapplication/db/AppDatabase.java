package com.example.myapplication.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.myapplication.model.contacts;

@Database(entities = {contacts.class} , version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract AppDao appDao();
    public static volatile AppDatabase INSTANCE;
    public static AppDatabase getInstance(Context context){
        if(INSTANCE==null){
            synchronized (AppDatabase.class){
                if(INSTANCE==null)
                    INSTANCE= Room.databaseBuilder(context.getApplicationContext(),AppDatabase.class,"app.db")
                            .allowMainThreadQueries()
                            .build();
            }

        }

        return INSTANCE;
    }
}
