package com.educa62.simpleroom.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.educa62.simpleroom.entity.Players

@Database(entities = [Players::class], version = 2)
abstract class PlayerDatabase : RoomDatabase() {

    abstract fun playersDao(): PlayersDao

    companion object {
        private var INSTANCE: PlayerDatabase? = null

        fun createDatabase(context: Context): PlayerDatabase? {
            if (INSTANCE == null) {
                synchronized(PlayerDatabase::class) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                            PlayerDatabase::class.java, "players.db").allowMainThreadQueries()
                            .build()
                }
            }

            return INSTANCE
        }
    }
}