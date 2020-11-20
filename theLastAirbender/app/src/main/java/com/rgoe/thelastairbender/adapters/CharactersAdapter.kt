package com.rgoe.thelastairbender.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rgoe.thelastairbender.databinding.CardCharacterBinding
import com.rgoe.thelastairbender.listeners.OnBottomReachedListener
import com.rgoe.thelastairbender.models.CharacterView
import com.rgoe.thelastairbender.views.CharacterActivity
import kotlinx.android.synthetic.main.card_character.view.*

class CharactersAdapter: RecyclerView.Adapter<CharactersAdapter.CharacterViewHolder>() {

    private val listCharacters = mutableListOf<CharacterView>()
    //private var onBottomReachedListener: OnBottomReachedListener?= null

    public fun addResults(data: List<CharacterView>){
        listCharacters.addAll(data)
    }

    /*fun setOnBottomReachedListener(onBottomReachedListener: OnBotttomReachedListener){
        this.onBottomReachedListener = OnBottomReachedListener
    }*/

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val cardDataBinding = CardCharacterBinding.inflate(layoutInflater, parent, false)
        return CharacterViewHolder(cardDataBinding)
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        if(holder is CharacterViewHolder){
            val result = listCharacters[position]
            holder.onBind(result)
            holder.itemView.txtCharacterName.setOnClickListener{
                var intentCharacterActivity = Intent(it.context, CharacterActivity::class.java)
                intentCharacterActivity.putExtra("characterID", holder.itemView.txtCharacterID.text.toString())
                it.context.startActivity(intentCharacterActivity)
            }
        } else {
            //onBottomReachedListener?.onBottomReached()
        }
    }

    override fun getItemCount(): Int {
        return listCharacters.size
    }

    inner class CharacterViewHolder(private val cardCharacterBinding: CardCharacterBinding)
        : RecyclerView.ViewHolder(cardCharacterBinding.root){

        fun onBind(characterView: CharacterView){
            cardCharacterBinding.character = characterView
            cardCharacterBinding.executePendingBindings()
        }
    }
}