package com.pnytrainings.pnyjt1.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.pnytrainings.pnyjt1.ContactAdapter;
import com.pnytrainings.pnyjt1.MyDataBase;
import com.pnytrainings.pnyjt1.R;
import com.pnytrainings.pnyjt1.model.Contact;
import com.pnytrainings.pnyjt1.model.db.ContactEntity;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewActivity extends AppCompatActivity {

    RecyclerView contactsRV;

    ArrayList<Contact> contacts = new ArrayList<>();

    ContactAdapter contactAdapter;

    List<ContactEntity> contactEntities;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);

        contactsRV = findViewById(R.id.contactsRV);

        contactEntities =  MyDataBase.getAppDatabase(RecyclerViewActivity.this).contactDao()
                .getAll();

        contactAdapter = new ContactAdapter(RecyclerViewActivity.this,contactEntities);

        contactsRV.setLayoutManager(new LinearLayoutManager(this));
        contactsRV.setAdapter(contactAdapter);

    }

    public void populateDataa(){

        Contact contact = new Contact("hamza","12312312",R.drawable.avatar2);
        contacts.add(contact);
        Contact contact1 = new Contact("hamza 1","12312312",R.drawable.avatar1);
        contacts.add(contact1);
        Contact contact2 = new Contact("hamza 2","12312312",R.drawable.avatar4);
        contacts.add(contact2);
        Contact contact3 = new Contact("hamza 3","12312312",R.drawable.avatar3);
        contacts.add(contact3);
        Contact contact4 = new Contact("hamza 4 ","12312312",R.drawable.avatar4);
        contacts.add(contact4);
        Contact contact5 = new Contact("hamza 5","12312312",R.drawable.avatar1);
        contacts.add(contact5);
        Contact contact6 = new Contact("hamza 6 ","12312312",R.drawable.avatar2);
        contacts.add(contact6);
        Contact contact7 = new Contact("hamza 7","12312312",R.drawable.avatar4);
        contacts.add(contact7);
        Contact contact8 = new Contact("hamza 8","12312312",R.drawable.avatar3);
        contacts.add(contact8);
        Contact contact9= new Contact("hamza 9","12312312",R.drawable.avatar4);
        contacts.add(contact9);
        Contact contact10  = new Contact("hamza 10","12312312",R.drawable.avatar1);
        contacts.add(contact10);
        Contact contact11 = new Contact("hamza 11 ","12312312",R.drawable.avatar2);
        contacts.add(contact11);
        Contact contact61 = new Contact("hamza 61 ","12312312",R.drawable.avatar2);
        contacts.add(contact61);
        Contact contact71 = new Contact("hamza 71","12312312",R.drawable.avatar4);
        contacts.add(contact71);
        Contact contact81 = new Contact("hamza 81","12312312",R.drawable.avatar3);
        contacts.add(contact81);
        Contact contact91= new Contact("hamza 91","12312312",R.drawable.avatar4);
        contacts.add(contact91);
        Contact contact101  = new Contact("hamza 101","12312312",R.drawable.avatar1);
        contacts.add(contact101);
        Contact contact111 = new Contact("hamza 111 ","12312312",R.drawable.avatar2);
        contacts.add(contact111);

    }
}
