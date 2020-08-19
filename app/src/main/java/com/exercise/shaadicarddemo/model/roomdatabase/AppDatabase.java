package com.exercise.shaadicarddemo.model.roomdatabase;


import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {UserTable.class}, version = 1,exportSchema = false)

public abstract class AppDatabase extends RoomDatabase {
    public abstract UserDao userDao();
}

