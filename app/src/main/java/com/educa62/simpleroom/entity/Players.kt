package com.educa62.simpleroom.entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "players")
data class Players(
        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = "id") var id: Int?,
        @ColumnInfo(name = "player") var player_: String?,
        @ColumnInfo(name = "position") var position_: String?,
        @ColumnInfo(name = "age") var age_: String?,
        @ColumnInfo(name = "gender") var gender_: String?,
        @ColumnInfo(name = "country") var country_: String?
        ){
        constructor():this(null,"","","","","")
}