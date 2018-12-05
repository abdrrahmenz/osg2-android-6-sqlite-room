package com.educa62.simpleroom;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
interface TeamsDao {

    @Insert
    void insert(List<Teams> teamsList);

    @Query("SELECT * FROM teams ORDER BY team ASC")
    List<Teams> select();

    @Delete
    void delete(Teams teams);

    @Update
    void update(Teams teams);
}
