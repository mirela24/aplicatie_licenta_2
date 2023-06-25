package com.example.a3pagini

import android.content.res.XmlResourceParser
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.widget.ImageView
import com.example.a3pagini.MatriceRara
import org.xmlpull.v1.XmlPullParser


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

    //=====================================
    private lateinit var noduri: ArrayList<Nod>
    private lateinit var A:MatriceRara

    private lateinit var djk: Dijkstra
    //=====================================
    private lateinit var paint: Paint
    private lateinit var paint2: Paint
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

        creazaGraf()
    }
    private fun creazaGraf(){
        //citim informatiile despre noduri si matricea de adiacenta
        citesteMatriceAdiacenta()



    }
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

        paint2 = Paint()
        paint2.color = Color.BLUE
        paint2.strokeWidth = 14F

        var tempRect : Rect = Rect(0,0, bmpEtaj1.width,bmpEtaj1.height)
        //desenam imaginea etajului
        cnvEtaj1.drawBitmap(imgEtaj1,tempRect,tempRect,null)
        // desenam toate legaturile
        for(i in 0..noduri.size-1)
        {
            for(j in i+1..noduri.size-1)
            {
                if(A.get(i,j)!=0f) cnvEtaj1.drawLine(noduri[i].X+10, noduri[i].Y+15, noduri[j].X+10, noduri[j].Y+15, paint)
            }
        }
        //apoi celelalte etaje
        //desenam traseul

        //cnvEtaj1.drawLine(0F, 0F, tempRect.right.toFloat(),tempRect.bottom.toFloat(), paint)
        //............




        imgInterfata.invalidate()


    }
    public fun citesteNoduri() {
        noduri= ArrayList<Nod>()
        var nodCitit: Nod = Nod()
        var tagCitit: String = ""

        val xmlNoduri: XmlResourceParser =
            imgInterfata.context.resources.getXml(com.example.a3pagini.R.xml.noduri)
        xmlNoduri.next()
        var eventType: Int = xmlNoduri.getEventType()
        while (eventType != XmlPullParser.END_DOCUMENT) {
            if (eventType == XmlPullParser.START_TAG) {
                tagCitit = xmlNoduri.getName()
                if (tagCitit == "nod") {
                    nodCitit = Nod()
                }
            } else if (eventType == XmlPullParser.END_TAG) {
                if (xmlNoduri.getName() == "nod") {
                    noduri.add(nodCitit)
                }
            } else if (eventType == XmlPullParser.TEXT) {
                if (tagCitit == "id") nodCitit.Id = xmlNoduri.getText().toInt()
                if (tagCitit == "X") nodCitit.X = 2.75f*xmlNoduri.getText().toFloat()
                if (tagCitit == "Y") nodCitit.Y = 2.75f*xmlNoduri.getText().toFloat()
                if (tagCitit == "Z") nodCitit.Z = 2.75f*xmlNoduri.getText().toFloat()
                if (tagCitit == "nume") nodCitit.nume = xmlNoduri.getText().toString()
            }
            eventType = xmlNoduri.next()
        }
    }

    public fun daNod(id: Int):Nod {
        for(nod: Nod in noduri) {
            if(nod.Id==id)
                return nod
        }
        return Nod()
    }

    public fun daIndexNod(id: Int):Int {
        for(i in 0..noduri.size-1) {
            if(noduri[i].Id == id)
                return i
        }
        return -1
    }

    public fun citesteMatriceAdiacenta() {
        citesteNoduri()
       A = MatriceRara(noduri.size,noduri.size)
        var idNod1: Int =0
        var idNod2: Int =0
        var tagCitit: String = ""

        val xmlVecini: XmlResourceParser =
            imgInterfata.context.resources.getXml(com.example.a3pagini.R.xml.vecini)
        xmlVecini.next()
        var eventType: Int = xmlVecini.getEventType()
        while (eventType != XmlPullParser.END_DOCUMENT) {
            if (eventType == XmlPullParser.START_TAG) {
                tagCitit = xmlVecini.getName()
            } else if (eventType == XmlPullParser.END_TAG) {
                if (xmlVecini.getName() == "legatura") {
                    var n1:Nod = daNod(idNod1)
                    var n2:Nod = daNod(idNod2)
                    var distanta: Float
                    distanta = (Math.pow((n1.X-n2.X).toDouble(), 2.0) + Math.pow((n1.Y-n2.Y).toDouble(),
                        2.0
                    )).toFloat()
                    distanta = Math.sqrt(distanta.toDouble()).toFloat()
                    idNod1=daIndexNod(idNod1)
                    idNod2=daIndexNod(idNod2)
                    if(idNod1 >= 0 && idNod2 >=0)
                    {
                        A.set(idNod1,idNod2,distanta)
                    }
                }
            } else if (eventType == XmlPullParser.TEXT) {
                if (tagCitit == "idNod1") idNod1 = xmlVecini.getText().toInt()
                if (tagCitit == "idNod2") idNod2 = xmlVecini.getText().toInt()
            }
            eventType = xmlVecini.next()
        }

    }



    public fun calculeazaTraseu(start: Int, stop: Int){

        //construim imaginile pentru etaje
        creazaImaginiTraseu()
        //calculam si desenam traseul

        var traseuDJK: List<Int>
        try{
             djk = Dijkstra(noduri, A, start)
             traseuDJK = djk.daTraseu(stop)
        }catch (e: Exception) {
             traseuDJK = ArrayList<Int>()
        }
        for(i in 1..traseuDJK.size-1)

        {
            cnvEtaj1.drawLine(noduri[i].X+10, noduri[i].Y+15, noduri[i-1].X+10, noduri[i-1].Y+15, paint2)
        }
        imgInterfata.invalidate()
    }
}