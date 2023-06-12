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

        A

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
        var tempRect : Rect = Rect(0,0, bmpEtaj1.width,bmpEtaj1.height)
        //desenam imaginea etajului
        cnvEtaj1.drawBitmap(imgEtaj1,tempRect,tempRect,null)
        //apoi celelalte etaje
        //desenam traseul
        cnvEtaj1.drawLine(0F, 0F, tempRect.right.toFloat(),tempRect.bottom.toFloat(), paint)
        //............
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
                if (tagCitit == "X") nodCitit.X = xmlNoduri.getText().toFloat()
                if (tagCitit == "Y") nodCitit.Y = xmlNoduri.getText().toFloat()
                if (tagCitit == "Z") nodCitit.Z = xmlNoduri.getText().toFloat()
                if (tagCitit == "nume") nodCitit.nume = xmlNoduri.getText().toString()
            }
            eventType = xmlNoduri.next()
        }
    }
    public fun citesteMatriceAdiacenta() {
        citesteNoduri()
        A = MatriceRara(noduri.size,noduri.size)
        var indexNod1: Int =0
        var indexNod2: Int =0
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
                    var n1:Nod = noduri[indexNod1]
                    var n2:Nod = noduri[indexNod2]
                    var distanta:Float=1f//distanta dintre cele 2 noduri
                    A.set(indexNod1,indexNod2,distanta)
                }
            } else if (eventType == XmlPullParser.TEXT) {
                if (tagCitit == "idNod1") indexNod1 = xmlVecini.getText().toInt()
                if (tagCitit == "idNod2") indexNod2 = xmlVecini.getText().toInt()
            }
            eventType = xmlVecini.next()
        }
    }



    public fun calculeazaTraseu(start: Int, stop: Int){




        //determina traseul




        /*var traseuDJK: List<Int>
        var mAd: Array<IntArray?>

        mAd =
            Array<IntArray>(nrLin * nrCol) { IntArray(nrLin * nrCol) }!! //eventual matrice rara sau cu ad.
        traseuDJK = ArrayList()
        traseu.add(start)

        try {
            djk = Dijkstra(nrLin * nrCol, mAd, start)
            traseuDJK = djk.daTraseu(stop)
        } catch (e: Exception) {
            traseuDJK = ArrayList<Int>()
        }*/


        //........
        //construim imaginile pentru etaje
        creazaImaginiTraseu()
    }
}