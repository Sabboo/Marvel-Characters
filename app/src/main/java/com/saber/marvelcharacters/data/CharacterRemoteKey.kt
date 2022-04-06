package com.saber.marvelcharacters.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "remote_keys")
data class CharacterRemoteKey(
    @PrimaryKey
    @ColumnInfo(collate = ColumnInfo.NOCASE)
    val offset: String,
    val nextPageKey: String?
)
