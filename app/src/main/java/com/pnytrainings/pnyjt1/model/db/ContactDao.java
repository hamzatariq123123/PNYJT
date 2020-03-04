package com.pnytrainings.pnyjt1.model.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ContactDao {

    @Delete
    void deleteContact(ContactEntity contactEntity);

    @Insert
    void insertContact(ContactEntity contactEntity);

    @Update
    void updateContact(ContactEntity contactEntity);

    @Query("SELECT * FROM contact")
    List<ContactEntity> getAll();
}
