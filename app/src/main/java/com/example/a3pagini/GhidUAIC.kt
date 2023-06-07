package com.example.a3pagini

import android.graphics.*
import android.widget.ImageView


class GhidUAIC (imgZonaTraseu: ImageView, wImg: Int, hImg:Int){
    private lateinit var imgEtaj1: Bitmap
    //=====================================
    var etajCurent:Int = 1
    private lateinit var rSursa:Rect
    private lateinit var rDestiatie:Rect
    //=====================================
    var wTraseu: Int = 0
    var hTraseu: Int = 0
    private lateinit var imgInterfata: ImageView
    //=====================================
    private lateinit var bmpInterfata: Bitmap
    private lateinit var cnvInterfata: Canvas

    private lateinit var bmpEtaj1: Bitmap
    private lateinit var cnvEtaj1: Canvas
    ////o variabila "noduri" array list cu elemente de tip nod (clasa creata ant)
    private lateinit var noduri: Array<Nod>

    ///declar o variabila "mAD" de tip matrice cu elem float

    //=====================================
    private lateinit var paint: Paint
    init{
        //preluam informatiile de la constructorul principal
        imgInterfata = imgZonaTraseu
        wTraseu = wImg
        hTraseu = hImg
        // Cream un bitmap cu dimensiunile preluate
        bmpInterfata = Bitmap.createBitmap(wTraseu, hTraseu, Bitmap.Config.ARGB_8888)
        // Preluam panza(canvas-ul) corespunzatoare bitmap-ului
        cnvInterfata = Canvas(bmpInterfata)
        // Setam bitmap-ul drept continut pentru ImageView-ul specificat
        imgInterfata.setImageBitmap(bmpInterfata)
        //initializam resursele
        imgEtaj1 = BitmapFactory.decodeResource(imgInterfata.context.resources, com.example.a3pagini.R.drawable.etaj_secretariat)
        bmpEtaj1 = Bitmap.createBitmap(imgEtaj1.width, imgEtaj1.height, Bitmap.Config.ARGB_8888)
        cnvEtaj1 = Canvas(bmpEtaj1)

        rSursa=Rect(0,0,wImg,hImg)
        rDestiatie=Rect(0,0,wImg,hImg)

        ///apelam functia "citeste noduri"
    }

    ///declaram functia "citeste noduri" - ia elem din xml si le duce in nod de mai sus si adiacentele

    public fun mutaSursa(x: Float, y: Float){
        rSursa.offset(x.toInt()-rSursa.left, y.toInt()-rSursa.top)
    }
    public fun deseneaza(){
        if(etajCurent==1)
            cnvInterfata.drawBitmap(bmpEtaj1,rSursa,rDestiatie,paint)
        imgInterfata.invalidate()
    }
    private fun creazaImaginiTraseu(){
        paint = Paint()
        paint.color = Color.RED
        paint.strokeWidth = 11F
        var tempRect : Rect = Rect(0,0, bmpEtaj1.width,bmpEtaj1.height)
        //desenam imaginea etajului
        cnvEtaj1.drawBitmap(imgEtaj1,tempRect,tempRect,null)
        //apoi celelalte etaje
        //desenam traseul
        cnvEtaj1.drawLine(0F, 0F, tempRect.right.toFloat(),tempRect.bottom.toFloat(), paint)
        ///pt fiecare nod de la etajul 1 din "noduri" apelez cnvEtaj1.draw .. in pc de coord (x,y)
        //............
    }
    public fun calculeazaTraseu(){
        //determina traseul
        //........
        //construim imaginile pentru etaje
        creazaImaginiTraseu()
    }
}