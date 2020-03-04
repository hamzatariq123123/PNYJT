package com.pnytrainings.pnyjt1.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Dao;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.pnytrainings.pnyjt1.MyDataBase;
import com.pnytrainings.pnyjt1.R;
import com.pnytrainings.pnyjt1.model.db.ContactEntity;

import java.util.List;

public class DataBaseActivity extends AppCompatActivity {

    EditText name,number;
    Button save,update,delete,fetch;
    List<ContactEntity> contactEntities;
    String strName,strNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_base);

        name = findViewById(R.id.name);
        number = findViewById(R.id.number);
        save = findViewById(R.id.save);
        update = findViewById(R.id.update);
        delete = findViewById(R.id.delete);
        fetch = findViewById(R.id.fetch);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                MyDataBase.getAppDatabase(DataBaseActivity.this).contactDao()
                        .insertContact(getContact());
            }

        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                MyDataBase.getAppDatabase(DataBaseActivity.this).contactDao()
                        .updateContact(getContactwihID());
            }
        });

        fetch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(DataBaseActivity.this,RecyclerViewActivity.class));

              }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                MyDataBase.getAppDatabase(DataBaseActivity.this).contactDao()
                        .deleteContact(getContactwihID());
            }
        });
    }

    public ContactEntity getContact(){
        strName = name.getText().toString();
        strNumber = number.getText().toString();

        ContactEntity contactEntity = new ContactEntity();
        contactEntity.setName(strName);
        contactEntity.setContactNumber(strNumber);

        return contactEntity;
    }

    public ContactEntity getContactwihID(){
        strName = name.getText().toString();
        strNumber = number.getText().toString();

        ContactEntity contactEntity = new ContactEntity();
        contactEntity.setId(1);
        contactEntity.setName(strName);
        contactEntity.setContactNumber(strNumber);

        return contactEntity;
    }
}
