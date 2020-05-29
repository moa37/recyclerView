package com.example.myapplication.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.myapplication.model.contacts;

import java.util.List;

@Dao
public interface AppDao {
    @Query("SELECT * FROM contacts ")
    List<contacts> getallcontacts();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertall(contacts contact);

    @Delete
    void deleteContact(contacts... contact);

    @Update
    void updateContact(contacts... contact);
}
