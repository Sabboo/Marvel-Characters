package com.saber.marvelcharacters.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.saber.marvelcharacters.data.CharacterRemoteKey
import com.saber.marvelcharacters.data.MarvelCharacter


@Database(
    entities = [MarvelCharacter::class, CharacterRemoteKey::class],
    version = 1,
    exportSchema = false
)
abstract class MarvelDb : RoomDatabase() {
    companion object {
        fun create(context: Context): MarvelDb {
            val databaseBuilder = Room.databaseBuilder(context, MarvelDb::class.java, "marvel.db")
            return databaseBuilder
                .fallbackToDestructiveMigration()
                .build()
        }
    }

    abstract fun characters(): CharactersDao
    abstract fun remoteKeys(): MarvelCharactersRemoteKeyDao
}