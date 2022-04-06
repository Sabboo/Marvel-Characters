package com.saber.marvelcharacters.ui.adapter

import androidx.recyclerview.widget.RecyclerView
import com.saber.marvelcharacters.data.MarvelCharacter
import com.bumptech.glide.Glide
import com.saber.marvelcharacters.databinding.CharacterItemBinding


class CharacterViewHolder(private val characterItemBinding: CharacterItemBinding) :
    RecyclerView.ViewHolder(characterItemBinding.root) {

    fun bind(character: MarvelCharacter?) {
        characterItemBinding.heroName.text = character?.name
        Glide.with(characterItemBinding.heroImageView.context)
            .load(character?.thumbnail?.getFullPath()).into(characterItemBinding.heroImageView)
    }

}