package com.example.ha96666.baqala;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

@Database(entities = {Users.class},version = 1)
public abstract class UsersDatabase extends RoomDatabase {

    public abstract UsersDAO usersDAO();

    private static volatile UsersDatabase INSTANCE;

    static UsersDatabase getDatabase(final Context context){
        if (INSTANCE == null) {
            synchronized (UsersDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            UsersDatabase.class, "usersdb")
                            .allowMainThreadQueries()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
