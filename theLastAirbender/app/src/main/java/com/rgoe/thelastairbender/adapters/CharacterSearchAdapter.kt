package com.rgoe.thelastairbender.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rgoe.thelastairbender.databinding.CardCharacterSearchBinding
import com.rgoe.thelastairbender.models.CharacterView
import com.rgoe.thelastairbender.views.CharacterActivity
import kotlinx.android.synthetic.main.card_character_search.view.*

class CharacterSearchAdapter : RecyclerView.Adapter<CharacterSearchAdapter.CharacterSearchViewHolder>() {

    private val listCharacterSearch = mutableListOf<CharacterView>()

    public fun addCharacterSearch(data: List<CharacterView>){
        listCharacterSearch.addAll(data)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterSearchViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val cardCharacterSearchBinding = CardCharacterSearchBinding.inflate(layoutInflater, parent, false)
        return CharacterSearchViewHolder(cardCharacterSearchBinding)
    }

    override fun onBindViewHolder(holder: CharacterSearchViewHolder, position: Int) {
        if(holder is CharacterSearchViewHolder){
            val result = listCharacterSearch[position]
            holder.onBind(result)
            holder.itemView.txtCharacterSearchName.setOnClickListener{
                var intentCharacterSearch = Intent(it.context,CharacterActivity::class.java)
                intentCharacterSearch.putExtra("characterSearchID", holder.itemView.txtCharacterSearchID.text.toString())
                it.context.startActivity(intentCharacterSearch)
            }
        } else{

        }
    }

    override fun getItemCount(): Int {
        return listCharacterSearch.size
    }

    inner class CharacterSearchViewHolder (private val cardCharacterSearchBinding: CardCharacterSearchBinding)
        : RecyclerView.ViewHolder(cardCharacterSearchBinding.root){

        fun onBind(characterView: CharacterView){
            cardCharacterSearchBinding.characterSearch=characterView
            cardCharacterSearchBinding.executePendingBindings()
        }
    }
}