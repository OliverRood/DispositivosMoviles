package com.rgoe.bicho

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    private lateinit var etNombre : EditText
    private lateinit var etTelefono : EditText
    private lateinit var etMail : EditText

    private lateinit var chkPortugal : CheckBox
    private lateinit var chkSporting : CheckBox
    private lateinit var chkManchester : CheckBox
    private lateinit var chkRealMadrid : CheckBox
    private lateinit var chkJuventus : CheckBox

    private lateinit var rdFan1 : RadioButton
    private lateinit var rdFan2 : RadioButton
    private lateinit var rdFan3 : RadioButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etNombre = findViewById(R.id.etNombre)
        etTelefono = findViewById(R.id.etTelefono)
        etMail = findViewById(R.id.etEmail)

        rdFan1 = findViewById(R.id.rdFan1)
        rdFan2 = findViewById(R.id.rdFan2)
        rdFan3 = findViewById(R.id.rdFan3)

        chkPortugal = findViewById(R.id.chkPortugal)
        chkSporting = findViewById(R.id.chkSporting)
        chkManchester = findViewById(R.id.chkManUtd)
        chkRealMadrid = findViewById(R.id.chkRealMadrid)
        chkJuventus = findViewById(R.id.chkJuventus)

        chkPortugal.setOnCheckedChangeListener(changeChecked)
        chkSporting.setOnCheckedChangeListener(changeChecked)
        chkManchester.setOnCheckedChangeListener(changeChecked)
        chkRealMadrid.setOnCheckedChangeListener(changeChecked)
        chkJuventus.setOnCheckedChangeListener(changeChecked)

        val fab = findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener(fabClick)

    }

    fun createJerseys(): List<Jersey>{
        val jerseys = mutableListOf<Jersey>()
        if (chkPortugal.isChecked){
            jerseys.add(
                Jersey(
                    R.drawable.portugaljersey,
                    "Portugal",
                    "2003 - "
                )
            )
        }
        if (chkSporting.isChecked){
            jerseys.add(
                Jersey(
                    R.drawable.sportingjersey,
                    "Sporting",
                    "2002 - 2003"
                )
            )
        }
        if (chkManchester.isChecked){
            jerseys.add(
                Jersey(
                    R.drawable.manchesterjersey,
                    "Manchester",
                    "2003 - 2009"
                )
            )
        }
        if (chkRealMadrid.isChecked)
            jerseys.add(
                Jersey(
                    R.drawable.madridjersey,
                    "Real Madrid",
                    "2009 - 2018"
                )
            )
        if (chkJuventus.isChecked)
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

    private val fabClick = View.OnClickListener { fab ->
        var info = "Nombre: "+etNombre.text+"\nTelefono: "+etTelefono.text+"\nEmail: "+etMail.text+"\n\nNivel de Fan: "

        if(rdFan1.isChecked) {
            info += rdFan1.text
        } else if(rdFan2.isChecked) {
            info += rdFan2.text
        } else if (rdFan3.isChecked){
            info+=rdFan3.text}

        info+="\n\nEquipos de los cuales tiene un jersey del bicho:"
        if (chkPortugal.isChecked)
            info+="\n- "+chkPortugal.text
        if (chkSporting.isChecked)
            info+="\n- "+chkSporting.text
        if (chkManchester.isChecked)
            info+="\n- "+chkManchester.text
        if (chkRealMadrid.isChecked)
            info+="\n- "+chkRealMadrid.text
        if (chkJuventus.isChecked)
            info+="\n- "+chkJuventus.text

        val alertDialog = AlertDialog.Builder(fab.context)
            .setTitle("DATOS DEL BICHO FAN")
            .setMessage(info)
            .setPositiveButton("Ok", null)
            .setNegativeButton("No", null)
            .create()

        alertDialog.show()
    }
}