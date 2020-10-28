package com.rgoe.bichofans.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.rgoe.bichofans.R
import com.rgoe.bichofans.adapters.JerseyAdapter
import com.rgoe.bichofans.models.entities.Fan
import com.rgoe.bichofans.models.entities.Jersey
import com.rgoe.bichofans.viewmodels.addFanViewModel

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

    private val addFanViewModel: addFanViewModel by viewModels()

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
        fab.setOnClickListener{
            val intent = Intent(this, Fans::class.java)
            startActivity(intent)
        }

        val buttonSave = findViewById<MaterialButton>(R.id.button_save)
        buttonSave.setOnClickListener { fan ->
            val name = findViewById<EditText>(R.id.etNombre).text.toString()
            val phone = findViewById<EditText>(R.id.etTelefono).text.toString()
            val email = findViewById<EditText>(R.id.etEmail).text.toString()

            if(name.isNullOrEmpty() || phone.isNullOrEmpty() || email.isNullOrEmpty()){
                val alertDialog = AlertDialog.Builder(fan.context)
                    .setTitle("ERROR")
                    .setMessage("CAMPO VACIO AL AGREGAR")
                    .setPositiveButton("OK",null)
                    .create()
                alertDialog.show()
                return@setOnClickListener
            }

            var fanLvl = ""
            if (rdFan1.isChecked) {
                fanLvl += rdFan1.text
            } else if (rdFan2.isChecked) {
                fanLvl += rdFan2.text
            } else if (rdFan3.isChecked) {
                fanLvl += rdFan3.text}

            var jerseys="Equipos de los cuales tiene un jersey del bicho: "
            if (chkPortugal.isChecked)
                jerseys+="\n- "+chkPortugal.text
            if (chkSporting.isChecked)
                jerseys+="\n- "+chkSporting.text
            if (chkManchester.isChecked)
                jerseys+="\n- "+chkManchester.text
            if (chkRealMadrid.isChecked)
                jerseys+="\n- "+chkRealMadrid.text
            if (chkJuventus.isChecked)
                jerseys+="\n- "+chkJuventus.text

            val fan = Fan(
                name = name,
                phone = phone,
                mail = email,
                fanLvl = fanLvl,
                jerseys = jerseys
            )

            addFanViewModel.insertFan(fan)
            etNombre.text.clear()
            etTelefono.text.clear()
            etMail.text.clear()
            rdFan1.isChecked=true
            chkSporting.isChecked=false
            chkPortugal.isChecked=false
            chkManchester.isChecked=false
            chkJuventus.isChecked=false
            chkRealMadrid.isChecked=false
        }

        addFanViewModel.notifyInsertFan().observe(this) { succesful ->
            if (succesful){
                Toast.makeText(this,"Guardado exitoso",Toast.LENGTH_LONG).show()
            }else{
                Toast.makeText(this,"No se pudo completar el registro",Toast.LENGTH_LONG).show()
            }
        }
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
}