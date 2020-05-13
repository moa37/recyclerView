package com.example.myapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.myapplication.Adapter.contactAdapter;
import com.example.myapplication.Repository.AddContactRepository;
import com.example.myapplication.Repository.DeleteContactRepository;
import com.example.myapplication.Repository.contactReopsitory;
import com.example.myapplication.db.AppDatabase;
import com.example.myapplication.model.contacts;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ArrayList<contacts> list;
    AppDatabase db;
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

    contactAdapter.OnDeleteContact deleteContact=new contactAdapter.OnDeleteContact() {
        @Override
        public void OnDelete(contacts contact) {
            DeleteContactRepository del=new DeleteContactRepository(db,callback);
            del.execute(contact);
        }
    };

    contactAdapter adapter=new contactAdapter(onContactClicked,deleteContact);
  contactReopsitory.contactCallback callback=  new contactReopsitory.contactCallback() {
        @Override
        public void callBack(List<contacts> contactsList) {
            adapter.submitList(contactsList);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db=AppDatabase.getInstance(this);
        contactReopsitory repo=new contactReopsitory(db,callback );
        repo.execute();

//       db.appDao().insertall(new contacts("moatasem","012"));
//        List<contacts> dbContacts = db.appDao().getallcontacts();
  //      adapter.submitList(dbContacts);


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
            AddContactRepository addCon=new AddContactRepository(db,callback);
            addCon.execute((contacts) data.getSerializableExtra("contact"));
//            db.appDao().insertall((contacts) data.getSerializableExtra("contact"));
//            List<contacts> dbContacts=db.appDao().getallcontacts();
//            adapter.submitList(dbContacts);
        }if(requestCode==2&&resultCode==Activity.RESULT_OK){




            list.set(data.getIntExtra("post",0), (contacts) data.getSerializableExtra("contact"));
            ArrayList arrayList=new ArrayList<>(list);
            adapter.submitList(arrayList);

        }
    }



}
