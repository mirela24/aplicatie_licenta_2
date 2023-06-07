package com.example.a3pagini

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class Pagina2 : AppCompatActivity() {

    private lateinit var Traseu: TextView
    private lateinit var PunctStart: TextView
    private lateinit var Destinatie: TextView
    private lateinit var p2_to_p3: Button
    private lateinit var p2_to_settings: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pagina2)

        p2_to_p3 = findViewById(R.id.ArataTraseu_button)
        p2_to_settings = findViewById(R.id.settings_button)

        Traseu = findViewById(R.id.Traseu)
        PunctStart = findViewById(R.id.PunctStart)
        Destinatie = findViewById(R.id.Destinatie)

        Traseu.text = "Alege traseul:".trimIndent()
        PunctStart.text = "Punct de start:".trimIndent()
        Destinatie.text = "Destinație:".trimIndent()

        p2_to_p3.setOnClickListener {
            val intent = Intent(this, Pagina3::class.java)
            startActivity(intent)
        }
        p2_to_settings.setOnClickListener {
            val intent = Intent(this, Pagina4::class.java)
            startActivity(intent)
        }

        val Spinner1: Spinner = findViewById(R.id.spinner1)
        ArrayAdapter.createFromResource(
            this,
            R.array.puncte_start_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            Spinner1.adapter = adapter
        }

        val Spinner2: Spinner = findViewById(R.id.spinner2)
        ArrayAdapter.createFromResource(
            this,
            R.array.destinatii_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            Spinner2.adapter = adapter
        }

    }
}




