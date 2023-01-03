package com.example.a3pagini

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import android.view.View
import android.widget.*

class Pagina2 : AppCompatActivity() {

    // define the global variable
    private lateinit var question2: TextView
    private lateinit var question3: TextView
    private lateinit var question4: TextView
    // Add button Move to next Activity and previous Activity
    private lateinit var next_button: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pagina2)

        // by ID we can use each component which id is assign in xml
        // file use findViewById() to get the both Button and textview
        next_button = findViewById(R.id.second_activity_next_button)
        //previous_button = findViewById(R.id.second_activity_previous_button)
        question2 = findViewById(R.id.question2_id)
        question3 = findViewById(R.id.question3_id)
        question4 = findViewById(R.id.question4_id)

        // In question1 get the TextView use by findViewById()
        // In TextView set question Answer for message
        question2.text = "Alege traseul:".trimIndent()
        question3.text = "Punct de start:".trimIndent()
        question4.text = "Destinație:".trimIndent()

        // Add_button add clicklistener
        next_button.setOnClickListener {
            // Intents are objects of the android.content.Intent type. Your code can send them to the Android system defining
            // the components you are targeting. Intent to start an activity called ThirdActivity with the following code.
            val intent = Intent(this, Pagina3::class.java)
            // start the activity connect to the specified class
            startActivity(intent)
        }

        val Spinner1: Spinner = findViewById(R.id.spinner1) //Spinner1 de numele var de tip spinner
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter.createFromResource(
            this,
            R.array.puncte_start_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            Spinner1.adapter = adapter
        }

        val Spinner2: Spinner = findViewById(R.id.spinner2)
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter.createFromResource(
            this,
            R.array.destinatii_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            Spinner2.adapter = adapter
        }

    }}




