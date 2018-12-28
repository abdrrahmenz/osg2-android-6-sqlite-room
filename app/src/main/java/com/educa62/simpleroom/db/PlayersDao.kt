package com.educa62.simpleroom.db

import android.arch.persistence.room.*
import com.educa62.simpleroom.entity.Players

@Dao
interface PlayersDao {

    @Insert
    fun insert(playerList: List<Players>)

    @Query("SELECT * FROM players ORDER BY player ASC")
    fun select(): List<Players>

    @Delete
    fun delete(player: Players)

    @Update
    fun update(player: Players)
}