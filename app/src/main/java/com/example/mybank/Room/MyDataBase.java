package com.example.mybank.Room;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Entity;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Account.class},version =  2,exportSchema = false)
public abstract class MyDataBase extends RoomDatabase {
    private static MyDataBase myDataBase;
    private static String DATABASE_NAME = "database";
    public synchronized static MyDataBase getInstance(Context context){
        if (myDataBase == null){
            myDataBase = Room.databaseBuilder(context.getApplicationContext(),MyDataBase.class,DATABASE_NAME)
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigrationFrom(1,2)
                    .build();
        }


        return myDataBase;
    }

    static Migration MIGRATION_1_2 = new Migration(1,2) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE 'Account' ADD COLUMN 'Cust_AccNo' VARCHAR(16);");
            database.execSQL("ALTER TABLE 'Account' ADD COLUMN 'Cust_PhoneNumber' VARCHAR(10);");
            database.execSQL("ALTER TABLE 'Account' ADD COLUMN 'Cust_IFSC' VARCHAR(16);");
        }
    };

    public abstract DAO dao();
}
