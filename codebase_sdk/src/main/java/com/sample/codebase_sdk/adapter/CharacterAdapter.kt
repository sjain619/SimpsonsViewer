package com.sample.codebase_sdk.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sample.codebase_sdk.databinding.ListCharacterBinding
import com.sample.codebase_sdk.model.CharacterProfile

class CharacterAdapter(
    private val characters: MutableList<CharacterProfile> = mutableListOf(),
    private val itemClickListener: (CharacterProfile) -> Unit
) : RecyclerView.Adapter<CharacterAdapter.ViewHolder>() {

    //Update Character List
    fun updateChar(newChar: List<CharacterProfile>?) {
        characters.clear()
        if (newChar != null) {
            characters.addAll(newChar)
        }
        notifyDataSetChanged()
    }

    //onClick Logic
    inner class ViewHolder(
        private val view: ListCharacterBinding
    ) : RecyclerView.ViewHolder(view.root) {
        fun initUI(Character: CharacterProfile, clickListener: (CharacterProfile) -> Unit) {
            view.charName.text = Character.name

            itemView.setOnClickListener {
                clickListener(Character)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        ListCharacterBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun getItemCount() = characters.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.initUI(characters[position], itemClickListener)
    }

}