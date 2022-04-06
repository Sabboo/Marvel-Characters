package com.saber.marvelcharacters.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.saber.marvelcharacters.data.MarvelCharacter
import com.saber.marvelcharacters.databinding.CharacterItemBinding


class CharactersAdapter :
    PagingDataAdapter<MarvelCharacter, CharacterViewHolder>(MARVEL_CHARACTER_COMPARATOR) {

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun onBindViewHolder(
        holder: CharacterViewHolder,
        position: Int,
        payloads: MutableList<Any>
    ) {
        onBindViewHolder(holder, position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val itemBinding: CharacterItemBinding = CharacterItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return CharacterViewHolder(itemBinding)
    }

    companion object {
        val MARVEL_CHARACTER_COMPARATOR = object : DiffUtil.ItemCallback<MarvelCharacter>() {
            override fun areContentsTheSame(
                oldItem: MarvelCharacter,
                newItem: MarvelCharacter
            ): Boolean =
                oldItem == newItem

            override fun areItemsTheSame(
                oldItem: MarvelCharacter,
                newItem: MarvelCharacter
            ): Boolean =
                oldItem.name == newItem.name
        }
    }
}
