package com.MyTask.phonebook.model;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {PersonalDataModel.class,CodeModel.class}, version = 3)
public abstract class DataBase extends RoomDatabase {

    public abstract PersonalDatalDao PersonalDatalDao ();
    public abstract CodeDao CodeDatalDao ();

    private static volatile DataBase resumeDataBaseInstance;

    public static DataBase getDatabase(final Context context) {
        if (resumeDataBaseInstance == null) {
            synchronized (DataBase.class) {
                if (resumeDataBaseInstance == null) {
                    resumeDataBaseInstance = Room.databaseBuilder(context.getApplicationContext(),
                            DataBase.class, "DataBase")
                            .build();
                }
            }
        }
        return resumeDataBaseInstance;
    }


}
