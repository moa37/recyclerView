package com.example.myapplication.model;

public class contacts {
   private String contacts;
   private String  number;

    public contacts(String contacts, String number) {
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
}
