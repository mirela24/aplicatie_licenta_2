package com.example.a3pagini

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class Pagina1 : AppCompatActivity() {

    // define the global variable
    private lateinit var PrimulRand: TextView
    private lateinit var AlDoileaRand: TextView
    // Add button Move to Activity
    private lateinit var p1_to_p2: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pagina1)

        p1_to_p2 = findViewById(R.id.start_button)
        PrimulRand = findViewById(R.id.PrimulRand)
        AlDoileaRand = findViewById(R.id.AlDoileaRand)

        PrimulRand.text = "Universitatea \"Alexandru Ioan Cuza\" Iași".trimIndent()
        AlDoileaRand.text = "Facultatea de Matematică".trimIndent()

        p1_to_p2.setOnClickListener {

            val intent = Intent(this, Pagina2::class.java)
            startActivity(intent)
        }
    }
}
