package com.educa62.simpleroom;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

@Database(entities = Teams.class, version = 1)
abstract class FootballDatabase extends RoomDatabase {

    abstract TeamsDao teamsDao();

    private static FootballDatabase INSTANCE;

    static FootballDatabase createDatabase(Context context) {
        synchronized (FootballDatabase.class) {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(context, FootballDatabase.class, "db_football").allowMainThreadQueries().build();
            }
        }
        return INSTANCE;
    }
}
