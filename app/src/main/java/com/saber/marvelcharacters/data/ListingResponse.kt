package com.saber.marvelcharacters.data

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

data class ListingResponse(@SerializedName("data") val data: ListingData)

data class ListingData(
    @SerializedName("results") val results: List<MarvelCharacter>,
    @SerializedName("offset") val offset: String
)

@Entity
data class MarvelCharacter(
    @PrimaryKey @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String?,
    @Embedded @SerializedName("thumbnail") val thumbnail: CharacterThumbnail?
)

data class CharacterThumbnail(
    @SerializedName("path") val path: String,
    @SerializedName("extension") val extension: String
) {
    fun getFullPath() = "$path.$extension"
}