package com.pnytrainings.pnyjt1;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.pnytrainings.pnyjt1.model.db.ContactDao;
import com.pnytrainings.pnyjt1.model.db.ContactEntity;

@Database(entities = {ContactEntity.class}, version = 1)
public abstract class MyDataBase extends RoomDatabase {

    public abstract ContactDao contactDao();

    private static MyDataBase INSTANCE;

    public static MyDataBase getAppDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE =
                    Room.databaseBuilder(context.getApplicationContext(),
                            MyDataBase.class,
                            "user-database")
                            .allowMainThreadQueries()
                            .build();
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }


}
