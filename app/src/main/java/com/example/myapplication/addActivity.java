package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.Adapter.contactAdapter;
import com.example.myapplication.model.contacts;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.io.Serializable;

public class addActivity extends AppCompatActivity {
    EditText name,number;
    Button add;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        initComp();

        if(getIntent().hasExtra("contact")){
            contacts contact = (contacts) getIntent().getSerializableExtra("contact");
            name.setText(contact.getContacts());
            number.setText(contact.getNumber());

        }




    }

    void initComp(){

        add=findViewById(R.id.button);
        name=findViewById(R.id.ename);
        number=findViewById(R.id.enumber);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    contacts contact=createContact(name.getText().toString(),number.getText().toString());
                    if (contact != null) {
                        Intent intent = new Intent();
                        intent.putExtra("contact", contact);
                        intent.putExtra("mid",getIntent().getIntExtra("id",0));
                        setResult(Activity.RESULT_OK, intent);
                        finish();
                    }
                }
            }
        );
    }contacts createContact(String Sname,String Snumber){
        boolean invalid = false;
        if (Sname == null || Sname.isEmpty()) {
            invalid = true;
            name.setError("لا يمكن إضافة مستخدم بدون اسم");
        }
        if (Snumber == null || Snumber.isEmpty()) {
            invalid = true;
            number.setError("لا يمكن إضافة مستخدم بدون رقم ");
        }
        if (invalid) return null;
        else return new contacts(Sname, Snumber);
    }

}

