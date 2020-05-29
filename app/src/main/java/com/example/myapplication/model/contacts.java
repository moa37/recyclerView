package com.example.myapplication.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;


@Entity(tableName = "contacts")
public class contacts implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private int id ;

    @ColumnInfo(name = "contact-name")
    private String contacts;

    @ColumnInfo(name = "contact-number")
    private String  number;

    public contacts(String contacts, String number )  {
        this.contacts = contacts;
        this.number = number;
    }

    public String getContacts() {
        return contacts;
    }

    public void setContacts(String contacts) {
        this.contacts = contacts;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
