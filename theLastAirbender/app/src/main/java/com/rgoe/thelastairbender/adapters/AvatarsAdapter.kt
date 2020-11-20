package com.rgoe.thelastairbender.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rgoe.thelastairbender.databinding.CardAvatarBinding
import com.rgoe.thelastairbender.models.AvatarView
import com.rgoe.thelastairbender.views.CharacterActivity
import kotlinx.android.synthetic.main.card_avatar.view.*

class AvatarsAdapter: RecyclerView.Adapter<AvatarsAdapter.AvatarViewHolder>() {

    private val listAvatars = mutableListOf<AvatarView>()

    public fun addAvatars(data: List<AvatarView>){
        listAvatars.addAll(data)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AvatarViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val cardDataBinding = CardAvatarBinding.inflate(layoutInflater, parent, false)
        return AvatarViewHolder(cardDataBinding)
    }

    override fun onBindViewHolder(holder: AvatarViewHolder, position: Int) {
        if(holder is AvatarViewHolder){
            val result = listAvatars[position]
            holder.onBind(result)
            holder.itemView.txtAvatarName.setOnClickListener{
                var intentCharacterActivity = Intent(it.context, CharacterActivity::class.java)
                intentCharacterActivity.putExtra("avatarID", holder.itemView.txtAvatarID.text.toString())
                it.context.startActivity(intentCharacterActivity)
            }
        } else {
            //onBottomReachedListener?.onBottomReached()
        }
    }

    override fun getItemCount(): Int {
        return listAvatars.size
    }

    inner class AvatarViewHolder(private val cardAvatarBinding: CardAvatarBinding)
        :RecyclerView.ViewHolder(cardAvatarBinding.root){

        fun onBind(avatarView: AvatarView){
            cardAvatarBinding.avatar = avatarView
            cardAvatarBinding.executePendingBindings()
        }
    }
}