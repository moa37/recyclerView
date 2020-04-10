package com.example.myapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.myapplication.Adapter.contactAdapter;
import com.example.myapplication.model.contacts;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.prefs.Preferences;

public class MainActivity extends AppCompatActivity {
    ArrayList<contacts> list;
    final int REQUEST_CODE=1;
    final int EDIT_CODE=2;
    contactAdapter.OnContactClicked onContactClicked = new contactAdapter.OnContactClicked() {
        @Override
        public void onContact(contacts contact, int position) {
            Intent intent = new Intent(MainActivity.this, addActivity.class);
            intent.putExtra("pos", position);
            intent.putExtra("contact", contact);
            startActivityForResult(intent, EDIT_CODE);


        }
    };
    contactAdapter adapter=new contactAdapter(onContactClicked);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        list=new ArrayList<>();
        contactAdapter.contactArray=list;



        FloatingActionButton fab =findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(MainActivity.this,addActivity.class),REQUEST_CODE);
            }
        });



        RecyclerView rec=findViewById(R.id.rec);
        rec.setAdapter(adapter);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1&& resultCode==Activity.RESULT_OK){
            list.add((contacts) data.getSerializableExtra("contact"));
            adapter.notifyDataSetChanged();
        }if(requestCode==2&&resultCode==Activity.RESULT_OK){
            list.set(data.getIntExtra("post",0), (contacts) data.getSerializableExtra("contact"));
            adapter.notifyDataSetChanged();


        }
    }


}
