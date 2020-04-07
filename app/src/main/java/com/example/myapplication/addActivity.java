package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class addActivity extends AppCompatActivity {
    TextInputLayout name,number;
    Button add;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        add=findViewById(R.id.button);
        name=findViewById(R.id.nameET);
        number=findViewById(R.id.numberET);
        final EditText EditNumber = number.getEditText();
        final EditText Editname=name.getEditText();

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                assert Editname != null;
                if(TextUtils.isEmpty(Editname.getText().toString())){
                    Editname.setError("This field is required");

                }
                assert EditNumber != null;
                if(TextUtils.isEmpty(EditNumber.getText().toString())){
                    EditNumber.setError("This field is required");
                }if(!TextUtils.isEmpty(EditNumber.getText().toString())&&!TextUtils.isEmpty(Editname.getText().toString())){
                    Intent intent=new Intent();
                    intent.putExtra("name",Editname.getText().toString());
                    intent.putExtra("number",EditNumber.getText().toString());
                    setResult(101,intent);
                    finish();
                }

            }

        });


    }

}

