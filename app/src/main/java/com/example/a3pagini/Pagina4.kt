package com.example.a3pagini

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import com.example.a3pagini.databinding.ActivityPagina4Binding

class Pagina4 : AppCompatActivity()
{
    lateinit var binding: ActivityPagina4Binding
    lateinit var selectedColor: ColorObject
    lateinit var p4_to_p2: Button


    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        binding =  ActivityPagina4Binding.inflate(layoutInflater)
        setContentView(binding.root)
        loadColorSpinner()

        p4_to_p2 = findViewById(R.id.Back_button)

        p4_to_p2.setOnClickListener {
            val intent = Intent(this, Pagina2::class.java)
            startActivity(intent)
        }

        val SpinnerAlgoritm: Spinner = findViewById(R.id.spinnerAlgoritm)
        ArrayAdapter.createFromResource(
            this,
            R.array.algoritm_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            SpinnerAlgoritm.adapter = adapter
        }

    }

    private fun loadColorSpinner()
    {
        selectedColor = ColorList().defaultColor
        binding.SpinnerTraseu.apply {
            adapter = ColorSpinnerAdapter(applicationContext, ColorList().basicColors())
            setSelection(ColorList().colorPosition(selectedColor), false)
            onItemSelectedListener = object : AdapterView.OnItemSelectedListener
            {
                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, position: Int, p3: Long)
                {
                    selectedColor = ColorList().basicColors()[position]
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {}
            }
        }
        binding.SpinnerNod.apply {
            adapter = ColorSpinnerAdapter(applicationContext, ColorList().basicColors())
            setSelection(ColorList().colorPosition(selectedColor), false)
            onItemSelectedListener = object : AdapterView.OnItemSelectedListener
            {
                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, position: Int, p3: Long)
                {
                    selectedColor = ColorList().basicColors()[position]
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {}
            }
        }
        binding.SpinnerExtra.apply {
            adapter = ColorSpinnerAdapter(applicationContext, ColorList().basicColors())
            setSelection(ColorList().colorPosition(selectedColor), false)
            onItemSelectedListener = object : AdapterView.OnItemSelectedListener
            {
                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, position: Int, p3: Long)
                {
                    selectedColor = ColorList().basicColors()[position]
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {}
            }
        }
    }
}



