package com.example.student.application;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

/**
 * Created by student on 11/3/2017 AD.
 */

@Database(entities = {MessageInfo.class}, version = 1)
public abstract class MessageInfoDB extends RoomDatabase {
    public abstract MessageInfoDAO getMessageInfo();
}
