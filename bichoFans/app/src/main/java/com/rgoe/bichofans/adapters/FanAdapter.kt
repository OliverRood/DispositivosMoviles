package com.rgoe.bichofans.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.rgoe.bichofans.R
import com.rgoe.bichofans.models.entities.Fan

class FanAdapter(private val contents: List<Fan>) : RecyclerView.Adapter<FanAdapter.FanAdapterViewHolder>()  {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FanAdapterViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.fan_card, parent, false)

        return FanAdapterViewHolder(view)
    }

    override fun onBindViewHolder(holder: FanAdapterViewHolder, position: Int) {
        val content = contents[position]
        holder.onBind(content)
    }

    override fun getItemCount(): Int {
        return contents.size
    }

    class FanAdapterViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        fun onBind(content: Fan) {
            val textViewName = view.findViewById<TextView>(R.id.txtName)
            val textViewPhone = view.findViewById<TextView>(R.id.txtPhone)
            val textViewMail = view.findViewById<TextView>(R.id.txtMail)
            val textViewFanlvl = view.findViewById<TextView>(R.id.txtFanLvl)
            val textViewJerseys = view.findViewById<TextView>(R.id.txtJerseys)

            textViewName.text = content.name
            textViewPhone.text = content.phone
            textViewMail.text = content.mail
            textViewFanlvl.text = content.fanLvl
            textViewJerseys.text = content.jerseys
        }
    }
}