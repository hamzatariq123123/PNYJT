package com.pnytrainings.pnyjt1;

import android.app.Activity;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pnytrainings.pnyjt1.activity.DataBaseActivity;
import com.pnytrainings.pnyjt1.model.Contact;
import com.pnytrainings.pnyjt1.model.db.ContactEntity;

import java.util.ArrayList;
import java.util.List;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.contactViewHolder> {

    Activity activity;
    List<ContactEntity> contacts;

    public ContactAdapter(Activity activity, List<ContactEntity> contacts) {
        this.activity = activity;
        this.contacts = contacts;
    }

    @NonNull
    @Override
    public contactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(viewType ==0){

            View itemView = LayoutInflater.from(activity)
                    .inflate(R.layout.row_contact, parent, false);

            return new contactViewHolder(itemView);
        }
        else {
            View itemView = LayoutInflater.from(activity)
                    .inflate(R.layout.row_even, parent, false);

            return new contactViewHolder(itemView);
        }

    }

    @Override
    public void onBindViewHolder(@NonNull contactViewHolder holder, final int position) {

        final ContactEntity contact = contacts.get(position);

        holder.userName.setText(contact.getName());
        holder.userContact.setText(contact.getContactNumber());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyDataBase.getAppDatabase(activity).contactDao()
                        .deleteContact(getContactwihID(contact.getId()));
                contacts =   MyDataBase.getAppDatabase(activity).contactDao()
                        .getAll();
                notifyDataSetChanged();
            }
        });

    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }

    @Override
    public int getItemViewType(int position) {
        if(position%2==0){
            return 0;
        }else {
            return 1;
        }
    }

    class contactViewHolder extends RecyclerView.ViewHolder {

        ImageView profilePic;
        TextView userName;
        TextView userContact;

        public contactViewHolder(@NonNull View itemView) {
            super(itemView);

            profilePic = itemView.findViewById(R.id.profilePic);
            userName = itemView.findViewById(R.id.userName);
            userContact = itemView.findViewById(R.id.userContact);
        }
    }


    public ContactEntity getContactwihID(int pos){

        ContactEntity contactEntity = new ContactEntity();
        contactEntity.setId(pos);
        contactEntity.setName("edit");
        contactEntity.setContactNumber("edit number 12321");

        return contactEntity;
    }
}
