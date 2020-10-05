package com.rgoe.bicho

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.CheckBox
import android.widget.CompoundButton
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var chkSporting : CheckBox
    private lateinit var chkManchester : CheckBox
    private lateinit var chkRealMadrid : CheckBox
    private lateinit var chkJuventus : CheckBox

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        chkSporting = findViewById(R.id.chkSporting)
        chkManchester = findViewById(R.id.chkManUtd)
        chkRealMadrid = findViewById(R.id.chkRealMadrid)
        chkJuventus = findViewById(R.id.chkJuventus)

        chkSporting.setOnCheckedChangeListener(changeChecked)
        chkManchester.setOnCheckedChangeListener(changeChecked)
        chkRealMadrid.setOnCheckedChangeListener(changeChecked)
        chkJuventus.setOnCheckedChangeListener(changeChecked)
    }

    fun createJerseys(): List<Jersey>{
        val jerseys = mutableListOf<Jersey>()
        if(chkSporting.isChecked){
            jerseys.add(
                Jersey(
                    R.drawable.sportingjersey,
                    "Sporting",
                    "2002 - 2003"
                )
            )
        }
        if(chkManchester.isChecked){
            jerseys.add(
                Jersey(
                    R.drawable.manchesterjersey,
                    "Manchester",
                    "2003 - 2009"
                )
            )
        }
        if(chkRealMadrid.isChecked)
            jerseys.add(
                Jersey(
                    R.drawable.madridjersey,
                    "Real Madrid",
                    "2009 - 2018"
                )
            )
        if(chkJuventus.isChecked)
            jerseys.add(
                Jersey(
                    R.drawable.juventusjersey,
                    "Juventus",
                    "2018 - "
                )
            )
        return jerseys
    }

    private val changeChecked = CompoundButton.OnCheckedChangeListener{ button, checked ->
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
        val jerseyAdapter = JerseyAdapter(createJerseys())
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        recyclerView.adapter = jerseyAdapter
        jerseyAdapter.notifyDataSetChanged()
    }
}