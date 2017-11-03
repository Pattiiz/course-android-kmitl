package com.example.student.application;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

/**
 * Created by student on 11/3/2017 AD.
 */

@Dao
interface MessageInfoDAO {

    @Query("Select * From Message")
    List<MessageInfo> getAll();

    @Insert
    void insert(MessageInfo messageInfo);
}
