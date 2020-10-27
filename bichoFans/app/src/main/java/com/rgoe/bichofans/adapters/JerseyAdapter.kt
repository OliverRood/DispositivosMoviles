package com.rgoe.bichofans.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.rgoe.bichofans.R
import com.rgoe.bichofans.models.entities.Jersey

class JerseyAdapter (private val jerseys: List<Jersey>) : RecyclerView.Adapter<JerseyAdapter.JerseyHolder>(){

    class JerseyHolder (val view: View) : RecyclerView.ViewHolder(view){
        fun onBind(jersey: Jersey){
            val image = view.findViewById<ImageView>(R.id.image)
            val team = view.findViewById<TextView>(R.id.txtTeam)
            val season = view.findViewById<TextView>(R.id.txtSeason)

            image.setImageResource(jersey.image)
            team.text = jersey.team
            season.text = jersey.season
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JerseyHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.jersey_card, parent, false)
        return JerseyHolder(view)
    }

    override fun onBindViewHolder(holder: JerseyHolder, position: Int) {
        val jersey = jerseys[position]
        holder.onBind(jersey)
    }

    override fun getItemCount(): Int {
        return jerseys.size
    }


}