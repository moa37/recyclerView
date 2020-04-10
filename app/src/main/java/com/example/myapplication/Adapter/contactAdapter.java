package com.example.myapplication.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.chauthai.swipereveallayout.SwipeRevealLayout;
import com.chauthai.swipereveallayout.ViewBinderHelper;
import com.example.myapplication.model.contacts;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;

import java.util.ArrayList;

public class contactAdapter extends RecyclerView.Adapter<contactAdapter.holder> {
    Context context;
    public static ArrayList<contacts> contactArray=new ArrayList<>();
   public OnContactClicked onContactClicked;

    public contactAdapter(OnContactClicked onContactClicked) {
        this.onContactClicked = onContactClicked;
    }

    private final ViewBinderHelper viewBinderHelper = new ViewBinderHelper();
    public interface OnContactClicked{
         void onContact(contacts contact,int position);
    }

    public contactAdapter(Context context,ArrayList<contacts> contactArray) {
        this.contactArray = contactArray;
        this.context=context;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.datalayout, parent, false);
        return new holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull holder holder, final int position) {
        final contacts contacts = contactArray.get(position);
        holder.name.setText(contacts.getContacts());
        holder.number.setText(contacts.getNumber());
        viewBinderHelper.setOpenOnlyOne(true);
        viewBinderHelper.bind(holder.swipeLayout,String.valueOf(contactArray.get(position)));
        viewBinderHelper.closeLayout(String.valueOf(contactArray.get(position)));

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                contactArray.remove(contacts);
                notifyDataSetChanged();
            }
        });

        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onContactClicked.onContact(contacts,position);

            }
        });



    }


    @Override
    public int getItemCount() {
        return contactArray!=null?contactArray.size():0;
    }

    class holder extends RecyclerView.ViewHolder{
        TextView name,number;
        ImageView imageV,delete,edit;
        SwipeRevealLayout swipeLayout;
        public holder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.name);
            number=itemView.findViewById(R.id.number);
            imageV=itemView.findViewById(R.id.imageV);
            delete=itemView.findViewById(R.id.del);
            swipeLayout=itemView.findViewById(R.id.swipeL);
            edit=itemView.findViewById(R.id.edit);

        }
    }
}
