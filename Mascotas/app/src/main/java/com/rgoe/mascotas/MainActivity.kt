package com.rgoe.mascotas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        val animalAdapter = AnimalAdapter(createAnimals())

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = animalAdapter

        animalAdapter.notifyDataSetChanged()
    }

    fun createAnimals(): List<Animal> {
        val animals = mutableListOf<Animal>()

        animals.add(
            Animal(
                R.drawable.ic_maki,
                "Sushi",
                "Es un plato típico de origen japonés basado en arroz y combinado con " +
                        "otros ingredientes."
            )
        )

        animals.add(
            Animal(
                R.drawable.ic_burger,
                "Hamburguesa",
                "Es un tipo de sándwich hecho a base de carne molida aglutinada en forma " +
                        "de filete cocinado a la parrilla o a la plancha"
            )
        )

        animals.add(
            Animal(
                R.drawable.ic_waffle,
                "Waffle",
                "Es una especie de galleta con masa crujiente parecida a un barquillo, de " +
                        "tipo oblea de origen belga que se cocina entre dos planchas calientes"
            )
        )

        return animals
    }
}