package com.example.a3pagini

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class Pagina3 : AppCompatActivity() {

    var etajCurent: Int = 0
    // Add button Move previous activity
    private lateinit var p3_to_p2: Button
    private lateinit var p3_to_ghid: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pagina3)

        p3_to_p2 = findViewById(R.id.Prev_button)
        p3_to_ghid = findViewById(R.id.Ghid_button)


        p3_to_p2.setOnClickListener {
            val intent = Intent(this, Pagina2::class.java)
            startActivity(intent)
        }

        p3_to_ghid.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        /* Pentru a selecta etajul si a schimba poza afisata. Nu se mai deschide pagina la adaugare
        tEtaj1 = findViewById(R.id.tab_etaj1)
        tEtaj1.setOnClickListener{
            etajCurent = 1
        }

        tParter = findViewById(R.id.tab_parter)
        tParter.setOnClickListener{
            etajCurent = 2
        }

        tSubsol = findViewById(R.id.tab_subsol)
        tParter.setOnClickListener{
            etajCurent = 3
        }
        */
    }
}
