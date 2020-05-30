package com.example.myapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.myapplication.Adapter.contactAdapter;
import com.example.myapplication.Repository.AddContactRepository;
import com.example.myapplication.Repository.ContactExecutor;
import com.example.myapplication.Repository.DeleteContactRepository;
import com.example.myapplication.Repository.UpdateRepository;
import com.example.myapplication.Repository.contactRepository;
import com.example.myapplication.db.AppDatabase;
import com.example.myapplication.model.contacts;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ArrayList<contacts> list;
    AppDatabase db;
    private ContactExecutor contactExecutor;
    final int REQUEST_CODE=1;
    final int EDIT_CODE=2;

    contactAdapter.OnContactClicked onContactClicked = new contactAdapter.OnContactClicked() {
        @Override
        public void onContact(contacts contact)
        {
            Intent intent = new Intent(MainActivity.this, addActivity.class);
            intent.putExtra("contact", contact);
            startActivityForResult(intent, EDIT_CODE);
        }
    };

    //delete data
    contactAdapter.OnDeleteContact deleteContact=new contactAdapter.OnDeleteContact() {
        @Override
        public void OnDelete(contacts contact)
        {
            contactExecutor.deleteContact(contact);
        }
    };



    contactAdapter adapter=new contactAdapter(onContactClicked,deleteContact);
  contactRepository.contactCallback callback=  new contactRepository.contactCallback() {
        @Override
        public void callBack(List<contacts> contactsList)
        {
            list=new ArrayList<>(contactsList);
            adapter.submitList(list);
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db=AppDatabase.getInstance(this);
        contactRepository repo=new contactRepository(db,callback );
        repo.execute();

         contactExecutor=new ContactExecutor(db.appDao(),callback);


        init();

    }

    private void init()
    {
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
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1&& resultCode==Activity.RESULT_OK)
        {

            //insert data
            contactExecutor.addContact((contacts) data.getSerializableExtra("cont"));

        }
        if(requestCode==2&&resultCode==Activity.RESULT_OK)
        {

          //update data
            contactExecutor.updateContact((contacts) data.getSerializableExtra("cont"));
            Log.d("TAG", "ContactNumber: "+ ((contacts) data.getSerializableExtra("cont")).getNumber());
        }
    }



}
