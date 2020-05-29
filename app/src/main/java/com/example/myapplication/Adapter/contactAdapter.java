package com.example.myapplication.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.chauthai.swipereveallayout.SwipeRevealLayout;
import com.chauthai.swipereveallayout.ViewBinderHelper;
import com.example.myapplication.Repository.UpdateRepository;
import com.example.myapplication.model.contacts;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.List;

public class contactAdapter extends ListAdapter<contacts,contactAdapter.holder> {
    public static List<contacts> contactArray=new ArrayList<>();
   public OnContactClicked onContactClicked;
    public OnDeleteContact deleteContact;


    static DiffUtil.ItemCallback<contacts> diffCallback=new DiffUtil.ItemCallback<contacts>() {
        @Override
        public boolean areItemsTheSame(@NonNull contacts oldItem, @NonNull contacts newItem) {
            return oldItem.getId()==newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull contacts oldItem, @NonNull contacts newItem) {
            return oldItem.getContacts().equals(newItem.getContacts()) && oldItem.getNumber().equals(oldItem.getNumber());
        }
    };

    public contactAdapter(OnContactClicked onContactClicked,OnDeleteContact deleteContact) {
        super(diffCallback);
        this.onContactClicked=onContactClicked;
        this.deleteContact=deleteContact;
    }

    public interface OnContactClicked{
        void onContact(contacts contact, int position);
    }

    public interface OnDeleteContact{
      void OnDelete(contacts contact);
    }


    @NonNull
    @Override
    public holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.datalayout, parent, false);
        return new holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull holder holder, final int position) {
        holder.name.setText(getItem(position).getContacts()+"  "+position);
        holder.number.setText(getItem(position).getNumber());
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteContact.OnDelete(getItem(position));

            }
        });

        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onContactClicked.onContact(getItem(position),position);

            }
        });



    }

    @Override
    public void submitList(@Nullable List<contacts> list) {
        super.submitList(list);
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
