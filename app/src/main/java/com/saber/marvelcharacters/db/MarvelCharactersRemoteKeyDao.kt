package com.saber.marvelcharacters.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.saber.marvelcharacters.data.CharacterRemoteKey

@Dao
interface MarvelCharactersRemoteKeyDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(keys: CharacterRemoteKey)

    @Query("SELECT * FROM remote_keys where `offset` = :offset")
    suspend fun remoteKeyByOffset(offset: String): CharacterRemoteKey

}